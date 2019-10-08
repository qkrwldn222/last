package com.sist.manager;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class API {
	public List<String> getThemeTourList() throws IOException, SAXException {
			List<String> list = new ArrayList<String>();
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getThemeTourList"); 
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+ "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D");																																 
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("350", "UTF-8")); 
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder;
			InputSource t_is = new InputSource();
			t_is.setCharacterStream(new StringReader(sb.toString()));
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(t_is);
				Element root = doc.getDocumentElement();
				NodeList node = root.getElementsByTagName("body");
				System.out.println(node.toString());
				Element channel = (Element) node.item(0);
				NodeList item = channel.getElementsByTagName("item");
				item=channel.getElementsByTagName("dataSid");
				String dataSid = "";
				for(int j = 0; j < item.getLength(); j++) {
					try {
						dataSid = item.item(j).getFirstChild().getNodeValue();
						System.out.println(dataSid);
						list.add(dataSid);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}		
		return list;
	}
	public void getThemeTourDetail(List<String> list) throws IOException, SAXException {
		JSONObject jsonObject = new JSONObject();
		 JSONArray arr = new JSONArray();
		JSONArray Array = new JSONArray();
			for(String no:list) {
			       StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getThemeTourDetail"); /*URL*/
			           urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D"); /*Service Key*/ 
			           urlBuilder.append("&" + URLEncoder.encode("data_sid","UTF-8") + "=" + URLEncoder.encode(no, "UTF-8")); 
			           URL url = new URL(urlBuilder.toString());
			           
			           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			           conn.setRequestMethod("GET");
			           conn.setRequestProperty("Content-type", "application/json");
			           System.out.println("Response code: " + conn.getResponseCode());
			           BufferedReader rd;
			           if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			               rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			           } else {
			               rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			           }
			           StringBuilder sb = new StringBuilder();
			           String line;
			           while ((line = rd.readLine()) != null) {
			               sb.append(line);
			           }
			           rd.close();
			           conn.disconnect();
			           JSONObject jsonObject1 = XML.toJSONObject(sb.toString());
			            JSONObject jsonObject2 = (JSONObject)jsonObject1.get("response");
			            JSONObject jsonObject3 = (JSONObject)jsonObject2.get("body");
			            JSONObject jsonObject4 = (JSONObject)jsonObject3.get("items");
			            JSONObject jsonObject5 = (JSONObject)jsonObject4.get("item");
			            jsonObject5.put("dataSid", no);
			            arr.put(jsonObject5);
				   }
				   try {
			           FileWriter file = new FileWriter("c:\\test\\ThemeTour.json");
			           file.write(arr.toString());
			           file.flush();
			           file.close();
			        } catch (IOException e) {
			           e.printStackTrace();
			        }
		}
	public List<String> RestaurantList(String no) throws IOException, SAXException {
		List<String> list = new ArrayList<String>();
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getRestaurantList"); 
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+ "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D");																																 
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); 
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(no, "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		InputSource t_is = new InputSource();
		t_is.setCharacterStream(new StringReader(sb.toString()));
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(t_is);
			Element root = doc.getDocumentElement();
			NodeList node = root.getElementsByTagName("body");
			Element channel = (Element) node.item(0);
			NodeList item = channel.getElementsByTagName("item");
			item=channel.getElementsByTagName("dataSid");
			String dataSid = "";
			for(int j = 0; j < item.getLength(); j++) {
				try {
					dataSid = item.item(j).getFirstChild().getNodeValue();
					list.add(dataSid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;
	}
	public String RestaurantDetail(List<String> list) throws IOException, SAXException {
		 JSONArray arr = new JSONArray();
		String json ="";
			for(String no:list) {
			       StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getRestaurantDetail"); /*URL*/
			           urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D"); /*Service Key*/ 
			           urlBuilder.append("&" + URLEncoder.encode("data_sid","UTF-8") + "=" + URLEncoder.encode(no, "UTF-8")); 
			           URL url = new URL(urlBuilder.toString());
			           
			           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			           conn.setRequestMethod("GET");
			           conn.setRequestProperty("Content-type", "application/json");
			           System.out.println("Response code: " + conn.getResponseCode());
			           BufferedReader rd;
			           if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			               rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			           } else {
			               rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			           }
			           StringBuilder sb = new StringBuilder();
			           String line;
			           while ((line = rd.readLine()) != null) {
			               sb.append(line);
			           }
			           rd.close();
			           conn.disconnect();
			           JSONObject jsonObject1 = XML.toJSONObject(sb.toString());
			            JSONObject jsonObject2 = (JSONObject)jsonObject1.get("response");
			            JSONObject jsonObject3 = (JSONObject)jsonObject2.get("body");
			            JSONObject jsonObject4 = (JSONObject)jsonObject3.get("items");
			            JSONObject jsonObject5 = (JSONObject)jsonObject4.get("item");
			            jsonObject5.put("dataSid",no);
			            arr.put(jsonObject5);
				   }
			json = arr.toString();
//				   try {
//			           FileWriter file = new FileWriter("c:\\test\\Restaurant.json");
//			           file.write(arr.toString());
//			           file.flush();
//			           file.close();
//			        } catch (IOException e) {
//			           e.printStackTrace();}
			return json;
		}
	public List<String> getTouristAttrList() throws IOException, SAXException {
		List<String> list = new ArrayList<String>();
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getRestaurantList"); 
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+ "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D");																																 
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); 
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		InputSource t_is = new InputSource();
		t_is.setCharacterStream(new StringReader(sb.toString()));
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(t_is);
			Element root = doc.getDocumentElement();
			NodeList node = root.getElementsByTagName("body");
			System.out.println(node.toString());
			Element channel = (Element) node.item(0);
			NodeList item = channel.getElementsByTagName("item");
			item=channel.getElementsByTagName("dataSid");
			String dataSid = "";
			for(int j = 0; j < item.getLength(); j++) {
				try {
					dataSid = item.item(j).getFirstChild().getNodeValue();
					System.out.println(dataSid);
					list.add(dataSid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void getTouristAttrDetail(List<String> list) throws IOException, SAXException {
		JSONObject jsonObject = new JSONObject();
		 JSONArray arr = new JSONArray();
		JSONArray Array = new JSONArray();
			for(String no:list) {
			       StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanTourInfoService/getTouristAttDetail"); /*URL*/
			           urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=%2Bcd%2BbGwLGvt2lmb1xThNt2jcw19ri%2Bpoy%2FCodf1Tt%2BCtJJ8EidfTuhWHGHpz7Ds55aTZFVHSInEXoonX1F%2FL0A%3D%3D"); /*Service Key*/ 
			           urlBuilder.append("&" + URLEncoder.encode("data_sid","UTF-8") + "=" + URLEncoder.encode(no, "UTF-8")); 
			           URL url = new URL(urlBuilder.toString());
			           
			           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			           conn.setRequestMethod("GET");
			           conn.setRequestProperty("Content-type", "application/json");
			           System.out.println("Response code: " + conn.getResponseCode());
			           BufferedReader rd;
			           if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			               rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			           } else {
			               rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			           }
			           StringBuilder sb = new StringBuilder();
			           String line;
			           while ((line = rd.readLine()) != null) {
			               sb.append(line);
			           }
			           rd.close();
			           conn.disconnect();
			           JSONObject jsonObject1 = XML.toJSONObject(sb.toString());
			            JSONObject jsonObject2 = (JSONObject)jsonObject1.get("response");
			            JSONObject jsonObject3 = (JSONObject)jsonObject2.get("body");
			            JSONObject jsonObject4 = (JSONObject)jsonObject3.get("items");
			            JSONObject jsonObject5 = (JSONObject)jsonObject4.get("item");
			            arr.put(jsonObject5);
				   }
				   try {
			           FileWriter file = new FileWriter("c:\\test\\TouristAttr.json");
			           file.write(arr.toString());
			           file.flush();
			           file.close();
			        } catch (IOException e) {
			           e.printStackTrace();
			  }
		}
}
	
