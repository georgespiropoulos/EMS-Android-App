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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class Crawler implements Runnable {
	private String url = "https://www.thessalonikiguide.gr/efimerevonta-nosokomeia/"; // https://www.xo.gr/efimerevonta-nosokomeia/
	private String city;
	private Document doc;
	private HashMap<String, ArrayList<String>> availableClinics;
	private List<Hospitals> listOfHospitals;
	ExecutorService executor;

	public Crawler(String city, List<Hospitals> listOfHospitals) {
		this.city = city;
		this.availableClinics = new HashMap<>();
		this.listOfHospitals = listOfHospitals;
		executor = Executors.newFixedThreadPool(10);
		executor.execute(this);

//		initialConnection(url);
//		getHospitalsFromCity();
	}

	public HashMap<String, ArrayList<String>> getAvailableClinics() {
		return availableClinics;
	}

	public List<Hospitals> getListOfHospitals() { return listOfHospitals; }

	private void initialConnection(String url) {
		try {
			doc = Jsoup.connect(url)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "text/html")
					.header("Accept-Encoding", "gzip,deflate")
					.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					.header("Connection", "keep-alive")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
					.get();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void getHospitalsFromCity() {
		//Elements citiesUrls = doc.select("PoplularRegions"); // ul.pharmacyCity
        //System.out.println(citiesUrls);
		switch (city) {
			case "thessalonikh":
				initialConnection("https://www.thessalonikiguide.gr/efimerevonta-nosokomeia/"); //https://www.xo.gr/efimerevonta-nosokomeia/thessaloniki/
				break;
			case "athina":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/athina/oles-oi-efimeries-nosokomeion/"); //https://www.xo.gr/efimerevonta-nosokomeia/athina/
				break;
			case "patra":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/patra/oles-oi-efimeries-nosokomeion/"); //https://www.xo.gr/efimerevonta-nosokomeia/nomos-patra/
				break;
			default:
				//for (Element ele : citiesUrls) {
				//	if (ele.attr("a").equals(city))
				//		initialConnection(ele.attr("href"));
				//}
				break;
		}

		Elements clinicsResult = doc.select("article.hospital"); // div.basicInfo // section.ClinicsResult
		for (Element clir : clinicsResult) {
			ArrayList<String> clList = new ArrayList<>();
			StringBuffer sb = new StringBuffer();
			for (Element clinic : clir.select("div.infogg span")) {
				if(clinic.nextElementSibling() != null)
					sb.append(clinic.text().replace('.', ',')).append(" ");
				else
					sb.append(clinic.text().replace('.', ' '));
			}
			clList.add(sb.toString());
			/*for (String clinic : clir.select("div.clinicsParts span").text().split(", ")) {
				if(clinic.contains(" "))
					clList.addAll(Arrays.asList(clinic.split(" ")));
				else
					clList.add(clinic);
			}*/

			clList.add(clir.select("span.adrdess").text()); // span.ClinicAdr // ("span.addressProfile span").text().split(" 546")[0]
			availableClinics.put(clir.select("h3.entry-title a").text(), clList); // h2.ClinicDescr // div.listingBusinessNameArea span -- .split("-")[1]
		}
	}

	public void getHospitalsFromCrawler() { // List<Hospitals>
		//List<Hospitals> listOfHospitals = new ArrayList<>();
		for (String name : availableClinics.keySet().toArray(new String[0])) {
			Hospitals hospital = new Hospitals(name,
					Objects.requireNonNull(availableClinics.get(name)).get(0),
					Objects.requireNonNull(availableClinics.get(name)).get(1));
			listOfHospitals.add(hospital);
		}
		//return listOfHospitals;
	}

	@Override
	public void run() {

		initialConnection(url);
		getHospitalsFromCity();
		getHospitalsFromCrawler();
		//System.out.println(listOfHospitals);
		executor.shutdown();
	}
}