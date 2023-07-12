package com.adobe.round2.service;

import com.adobe.round2.entity.AchievementInfo;
import com.adobe.round2.entity.Details;
import com.adobe.round2.entity.EducationInfo;
import com.adobe.round2.entity.ExperienceInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/*
 * This is the service class which contains the main logic behind this whole application
 */
@Service
public class MainService {

    @Autowired
    JSONDataService jsonDataService;

    @Autowired
    MergeDocumentToPDF mergeService;

    public ResponseEntity<Object> getPDFile(Details details) throws FileNotFoundException {

        /*
         * calls the jsonDatsService to get the JSON object
         */
        JSONObject jsonObject =  jsonDataService.getJSONObject(details);


        /*
         * get the appropriate template file location according to the ID provided
         */
        String docFile = null;
        if(details.getTemplate_id().equals("1")){
            docFile = "Template1/BasicTemplate.docx";
        } else if (details.getTemplate_id().equals("2")) {
            docFile = "Template2/LinkTemplate.docx";
        } else if (details.getTemplate_id().equals("3")) {
            docFile = "Template3/ImageTemplate.docx";
        }

        //making request to document merge api
        /*
         * mergeService method is called to get the file path of generated PDF file
         */
        String filePath = mergeService.mergeDocument(jsonObject , docFile);



//        File file = new File("output/merge2023-07-07T14-32-57.pdf");
        File file = new File(filePath);
        InputStream in =  new FileInputStream(file);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }



    /*
     * This method is to filter the Details class object
     * 1. converts string of skills to list of strings
     * 2. remove empty objects of Experience , Education , Achievements
     */
    public Details filteredInput(Details details){
        details.setSkills(Arrays.asList(details.getList_of_skills().split(",")));

        for(int i = details.getEducation().size() - 1 ; i >=0  ; i--){
            if(details.getEducation().get(i).getSchool_name().isEmpty()){
                details.getEducation().remove(i);
            }
        }

        for(int i = details.getExperience().size() - 1 ; i >=0  ; i--){
            if(details.getExperience().get(i).getCompany_name().isEmpty()){
                details.getExperience().remove(i);
            }
        }

        for(int i = details.getAchievements().size() - 1 ; i >=0  ; i--){
            if(details.getAchievements().get(i).getField().isEmpty()){
                details.getAchievements().remove(i);
            }
        }
        return details;
    }



    /*
     * This method is to initialize the Details class object so that using thymeleaf, input box can be dynamically spawned
     */
    public Details initializeDetails(){
        Details details = new Details();
        details.setExperience(List.of(new ExperienceInfo() , new ExperienceInfo() , new ExperienceInfo()));
        details.setEducation(List.of(new EducationInfo() , new EducationInfo() ,  new EducationInfo()));
        details.setAchievements(List.of(new AchievementInfo() ,  new AchievementInfo() , new AchievementInfo()));
        return details;
    }
}
