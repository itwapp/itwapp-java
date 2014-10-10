package io.itwapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.itwapp.exception.*;
import io.itwapp.rest.ApiRequest;

import java.util.*;

public class Interview {

    /**
     *  This is the unique identifier of the interview.
     */
    @SerializedName("_id")
    public String id;

    /**
     *  This is the name of the interview, as displayed to the applicant.
     */
    public String name;

    /**
     *  It will be displayed to the applicant in the interview interface only.
     */
    public Question[] questions;

    /**
     *  This is the link of the Youtube video chosen as the introduction video. It will be shown to the candidate before the interview starts.
     */
    public String video;

    /**
     *  This is a short description for the interview. It is displayed along with the Youtube video.
     */
    public String text;


    /**
     * This method retrieves all the interviews you created. These interviews will be returned sorted by creation date, the most recently created interview appearing on top of the list.
     * @param param a list of param. Parameter excepted is "next" : This parameter is used to paginate. It corresponds to the ID of an interview. When this ID is passed to the url, all interviews appearing after this ID in the list will be returned.
     * @return a list of interview
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Interview[] findAll(Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        String json;
        if(param.containsKey("next"))   {
            json = ApiRequest.get("/api/v1/interview/?next=" + param.get("next"));
        }else   {
            json = ApiRequest.get("/api/v1/interview/");
        }

        Gson gson = new Gson();
        return gson.fromJson(json, Interview[].class);
    }

    /**
     * This method retrieves a specific interview.
     * @param interviewId The ID of the interview to retrieve
     * @return the interview
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Interview findOne(String interviewId) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        String json = ApiRequest.get("/api/v1/interview/" + interviewId);
        Gson gson = new Gson();
        return gson.fromJson(json, Interview.class);
    }

    public static Applicant[] findAllApplicant(String interviewId, Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        String json;
        if(param.containsKey("next"))   {
            json = ApiRequest.get("/api/v1/interview/" + interviewId + "/applicant?next=" + param.get("next"));
        }else   {
            json = ApiRequest.get("/api/v1/interview/" + interviewId + "/applicant");
        }

        Gson gson = new Gson();
        return gson.fromJson(json, Applicant[].class);
    }

    private static final Set<Integer> acceptedValue = new HashSet<Integer>(Arrays.asList(
            new Integer[] {60, 120, 180, 240, 300}
    ));

    /**
     * This method creates an interview. If successful, it returns the new interview you created.
     * @param param see param on doc at http://api.itwapp.io/#create-an-interview
     * @return the interview just created
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Interview create(Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException  {

        if(!param.containsKey("name") || !param.containsKey("questions") || !param.containsKey("video") || !param.containsKey("text"))  {
            String missing = "missing param: ";

            missing += (!param.containsKey("name")) ? "name ": "";
            missing += (!param.containsKey("questions")) ? "questions ": "";
            missing += (!param.containsKey("video")) ? "video ": "";
            missing += (!param.containsKey("text")) ? "text ": "";

            throw new InvalidRequestError(missing);
        }else if(!(param.get("questions") instanceof List) || ((List)param.get("questions")).size() == 0 )    {
            throw new InvalidRequestError("wrong param questions");
        }

        List<Map<String, Object>> questions = (List<Map<String, Object>>) param.get("questions");
        for( Map<String, Object> question : questions ) {
            if(!question.containsKey("content") || !question.containsKey("readingTime") || !question.containsKey("answerTime") || !question.containsKey("number"))  {
                String missing = "missing param: ";
                missing += (!param.containsKey("content")) ? "content ": "";
                missing += (!param.containsKey("readingTime")) ? "readingTime ": "";
                missing += (!param.containsKey("answerTime")) ? "answerTime ": "";
                missing += (!param.containsKey("number")) ? "number ": "";

                throw new InvalidRequestError(missing);
            }else if( !acceptedValue.contains(question.get("readingTime")) || !acceptedValue.contains(question.get("answerTime")))  {
                throw new InvalidRequestError("invalid duration of readingTime " + param.get("readingTime") + " or answerTime " + param.get("answerTime"));
            }
        }


        String json = ApiRequest.post("/api/v1/interview/", param);
        Gson gson = new Gson();
        return gson.fromJson(json, Interview.class);
    }

    /**
     * This endpoint updates an interview. If successful, it returns the updated interview. You can’t update an interview that has already been sent to 1 or more applicants.
     * @param interviewId The ID of the interview to update
     * @param param see param on doc at http://api.itwapp.io/#update-an-interview
     * @return the updated interview
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Interview update(String interviewId, Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        if(!param.containsKey("name") || !param.containsKey("questions") || !param.containsKey("video") || !param.containsKey("text"))  {
            String missing = "missing param: ";

            missing += (!param.containsKey("name")) ? "name ": "";
            missing += (!param.containsKey("questions")) ? "questions ": "";
            missing += (!param.containsKey("video")) ? "name ": "";
            missing += (!param.containsKey("text")) ? "name ": "";

            throw new InvalidRequestError(missing);
        }else if(!(param.get("questions") instanceof List) || ((List)param.get("questions")).size() == 0 )    {
            throw new InvalidRequestError("wrong param questions");
        }

        List<Map<String, Object>> questions = (List<Map<String, Object>>) param.get("questions");
        for( Map<String, Object> question : questions ) {
            if(!question.containsKey("content") || !question.containsKey("readingTime") || !question.containsKey("answerTime") || !question.containsKey("number"))  {
                String missing = "missing param: ";
                missing += (!param.containsKey("content")) ? "content ": "";
                missing += (!param.containsKey("readingTime")) ? "readingTime ": "";
                missing += (!param.containsKey("answerTime")) ? "answerTime ": "";
                missing += (!param.containsKey("number")) ? "number ": "";

                throw new InvalidRequestError(missing);
            }else if( !acceptedValue.contains(question.get("readingTime")) || !acceptedValue.contains(question.get("answerTime")))  {
                throw new InvalidRequestError("invalid duration of readingTime " + param.get("readingTime") + " or answerTime " + param.get("answerTime"));
            }
        }


        String json = ApiRequest.put("/api/v1/interview/" + interviewId, param);
        Gson gson = new Gson();
        return gson.fromJson(json, Interview.class);
    }

    /**
     * This method delete a specific interview.
     * @param interviewId The ID of the interview to be deleted
     * @param param a list of param. Parameter excepted is "withApplicant" : Boolean default false. This parameter specifies whether all applicants corresponding to this interview must also be deleted or not.
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static void delete(String interviewId, Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        if(param.containsKey("withApplicant") && (Boolean) param.get("withApplicant"))  {
            ApiRequest.delete("/api/v1/interview/" + interviewId + "?withApplicant=true");
        }else   {
            ApiRequest.delete("/api/v1/interview/" + interviewId);
        }
    }
}
