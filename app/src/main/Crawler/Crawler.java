import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private String url;
	private String city;
	private Document doc;
	private HashMap<String, ArrayList<String>> availableClinics;
	
	public Crawler (String url, String city) {
		this.url = url;
		this.city = city;
		this.availableClinics = new HashMap<String, ArrayList<String>>();
		
		initialConnection(url);
		getHospitalsFromCity();
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		
		if (city.equals("thessalonikh"))
			initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/thessalonikh/oles-oi-efimeries-nosokomeion");
		else if (city.equals("athina")) {
			initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/athina/oles-oi-efimeries-nosokomeion");
		} else if (city.equals("patra"))
			initialConnection("https://www.vrisko.gr/efimeries-nosokomeion/patra/oles-oi-efimeries-nosokomeion");
		else
			for (Element ele : citiesUrls) {
				if (ele.attr("id").equals(city))
					initialConnection(ele.attr("href"));
			}
		
		Elements clinicsResult = doc.select("section.ClinicsResult");
		
		for (Element clir : clinicsResult) {
			ArrayList<String> clList = new ArrayList<String>();
			for (String clinic : Arrays.asList(clir.select("div.clinicsParts span").text().split(", "))) {
				if(clinic.contains(" "))
					for (String tempClinics : Arrays.asList(clinic.split(" ")))
						clList.add(tempClinics);
				else
					clList.add(clinic);
			}
			
			clList.add(clir.select("span.ClinicAdr").text());
			availableClinics.put(clir.select("h2.ClinicDescr").text(), clList);
		}
	}
}
