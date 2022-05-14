package ihuiee.webservices.Crawler;

import android.content.Context;

import androidx.room.RoomDatabase;

import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class Crawler {
	private String url = "https://www.vrisko.gr/efimeries-nosokomeion";
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
		Elements citiesUrls = doc.select("div.PoplularRegions ul li a");

		switch (city) {
			case "thessalonikh":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/thessalonikh/oles-oi-efimeries-nosokomeion");
				break;
			case "athina":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/athina/oles-oi-efimeries-nosokomeion");
				break;
			case "patra":
				initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/patra/oles-oi-efimeries-nosokomeion");
				break;
			default:
				for (Element ele : citiesUrls) {
					if (ele.attr("id").equals(city))
						initialConnection(ele.attr("href"));
				}
				break;
		}
		
		Elements clinicsResult = doc.select("section.ClinicsResult");
		
		for (Element clir : clinicsResult) {
			ArrayList<String> clList = new ArrayList<>();
			for (String clinic : clir.select("div.clinicsParts span").text().split(", ")) {
				if(clinic.contains(" "))
					clList.addAll(Arrays.asList(clinic.split(" ")));
				else
					clList.add(clinic);
			}
			
			clList.add(clir.select("span.ClinicAdr").text());
			availableClinics.put(clir.select("h2.ClinicDescr").text(), clList);
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
