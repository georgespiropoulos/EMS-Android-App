package ihuiee.webservices.Crawler;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class Crawler {
	private String url = "https://www.vrisko.gr/efimeries-nosokomeion"; // https://www.xo.gr/efimerevonta-nosokomeia/
	private String city;
	private Document doc;
	private HashMap<String, ArrayList<String>> availableClinics;

	public Crawler(String city) {
		this.city = city;
		this.availableClinics = new HashMap<>();

		initialConnection(url);
		getHospitalsFromCity();
	}

	public HashMap<String, ArrayList<String>> getAvailableClinics() {
		return availableClinics;
	}

	private void initialConnection(String url) {
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void getHospitalsFromCity() {
		Elements citiesUrls = doc.select("PoplularRegions"); // ul.pharmacyCity

		switch (city) {
			case "thessalonikh":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/thessalonikh/oles-oi-efimeries-nosokomeion/"); //https://www.xo.gr/efimerevonta-nosokomeia/thessaloniki/
				break;
			case "athina":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/athina/oles-oi-efimeries-nosokomeion/"); //https://www.xo.gr/efimerevonta-nosokomeia/athina/
				break;
			case "patra":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/patra/oles-oi-efimeries-nosokomeion/"); //https://www.xo.gr/efimerevonta-nosokomeia/nomos-patra/
				break;
			default:
				for (Element ele : citiesUrls) {
					if (ele.attr("a").equals(city))
						initialConnection(ele.attr("href"));
				}
				break;
		}

		Elements clinicsResult = doc.select("section.ClinicsResult"); // div.basicInfo
		for (Element clir : clinicsResult) {
			ArrayList<String> clList = new ArrayList<>();
			clList.add(clir.select("div.clinicsParts span").text());
			/*for (String clinic : clir.select("div.clinicsParts span").text().split(", ")) {
				if(clinic.contains(" "))
					clList.addAll(Arrays.asList(clinic.split(" ")));
				else
					clList.add(clinic);
			}*/
			clList.add(clir.select("span.ClinicAdr").text()); // ("span.addressProfile span").text().split(" 546")[0]
			availableClinics.put(clir.select("h2.ClinicDescr").text(), clList); // div.listingBusinessNameArea span -- .split("-")[1]
		}
	}

	public List<Hospitals> getHospitalsFromCrawler() {
		List<Hospitals> listOfHospitals = new ArrayList<>();
		for (String name : availableClinics.keySet().toArray(new String[0])) {
			Hospitals hospital = new Hospitals(name,
					Objects.requireNonNull(availableClinics.get(name)).get(0),
					Objects.requireNonNull(availableClinics.get(name)).get(1));
			listOfHospitals.add(hospital);
		}

		return listOfHospitals;
	}
}