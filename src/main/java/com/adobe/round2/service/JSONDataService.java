package com.adobe.round2.service;

import com.adobe.round2.entity.AchievementInfo;
import com.adobe.round2.entity.Details;
import com.adobe.round2.entity.EducationInfo;
import com.adobe.round2.entity.ExperienceInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


/*
 * getJSONObject() function is to get the JSON object which is compatible to be used in Adobe Document Generation API merge Document
 */
@Service
public class JSONDataService {
    public JSONObject getJSONObject(Details details){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Name" , details.getPersonal_information().getName());
            jsonObject.put("LastName" , details.getPersonal_information().getLast_name());
            jsonObject.put("EmailAddress" , details.getPersonal_information().getEmail_address());
            jsonObject.put("PhoneNumber" , details.getPersonal_information().getPhone_number());
            jsonObject.put("LinkedIn" ,"<a href="+ details.getPersonal_information().getLinkedin_url()+  ">linkedIn</a>" );
            jsonObject.put("JobTitle" , details.getJob_title());
            jsonObject.put("Summary" , details.getCareer_objective());
            jsonObject.put("Skills" , details.getSkills());


            JSONArray edu = new JSONArray();
            for(EducationInfo e : details.getEducation()){
                JSONObject t = new JSONObject();
                t.put("SchoolName" , e.getSchool_name());
                t.put("Year" , e.getPassing_year());
                t.put("Description" , e.getDescription());

                edu.put(t);
            }
            jsonObject.put("Education" , edu);



            JSONArray exp = new JSONArray();
            for(ExperienceInfo e : details.getExperience()){
                JSONObject t = new JSONObject();
                t.put("CompanyName" , e.getCompany_name());
                t.put("Year" , e.getPassing_year());
                t.put("Description" , e.getResponsibilities());

                exp.put(t);
            }
            jsonObject.put("Experience" , exp);


             JSONArray achieve = new JSONArray();
             for(AchievementInfo e : details.getAchievements()){
                 JSONObject t = new JSONObject();
                 t.put("Type" , e.getField());
                 t.put("Description" , e.getAwards());

                 achieve.put(t);
             }
             jsonObject.put("Achievements" , achieve);



            System.out.println(jsonObject);

            return jsonObject;
    }
}
