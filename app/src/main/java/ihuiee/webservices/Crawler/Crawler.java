package ihuiee.webservices.Crawler;

import android.content.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class Crawler {
	private String url = "https://www.xo.gr/efimerevonta-nosokomeia/";
	private String city;
	private Document doc;
	private HashMap<String, ArrayList<String>> availableClinics;

	public Crawler (String city) {
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
		Elements citiesUrls = doc.select("ul.pharmacyCity");

		switch (city) {
			case "Θεσσαλονίκη":
				initialConnection("https://www.xo.gr/efimerevonta-nosokomeia/thessaloniki/");
				break;
			case "Αθήνα":
				initialConnection("https://www.xo.gr/efimerevonta-nosokomeia/athina/");
				break;
			case "Πάτρα":
				initialConnection("https://www.xo.gr/efimerevonta-nosokomeia/nomos-patra/");
				break;
			default:
				for (Element ele : citiesUrls) {
					if (ele.attr("a").equals(city))
						initialConnection(ele.attr("href"));
				}
				break;
		}

		Elements clinicsResult = doc.select("div.basicInfo");
		for (Element clir : clinicsResult) {
			ArrayList<String> clList = new ArrayList<>();
			//System.out.println(clir.select("span.clinic").text());
			clList.add(clir.select("span.clinic").text());

			clList.add(clir.select("span.addressProfile span").text());
			availableClinics.put(clir.select("div.listingBusinessNameArea span").text(), clList);
		}
	}

	public void fillDB(Context context) {
		for (String name : availableClinics.keySet().toArray(new String[0])) {
			Hospitals hospital = new Hospitals(name,
					Objects.requireNonNull(availableClinics.get(name)).get(0),
					Objects.requireNonNull(availableClinics.get(name)).get(1));
			AppDatabase.getInstance(context).hospitalsDao().insertAll(hospital);
		}
	}
}