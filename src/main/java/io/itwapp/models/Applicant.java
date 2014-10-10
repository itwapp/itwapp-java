package io.itwapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.itwapp.exception.*;
import io.itwapp.rest.ApiRequest;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Applicant {

    /**
     * This is the applicant’s unique identifier.
     */
    @SerializedName("_id")
    public String id;

    /**
     * This is the applicant’s email.
     */
    public String mail;

    /**
     * This is the list of questions sent to the applicant.
     */
    public Question[] questions;

    /**
     * This is the list of video responses for the list of questions submitted.
     */
    public Response[] responses;

    /**
     * The identifier of the corresponding interview.
     */
    public String interview;

    /**
     * (timestamp in millisecond) The date on which the interview was sent to the applicant.
     */
    public long dateBegin;

    /**
     * (timestamp in millisecond) The deadline for the applicant to complete the interview.
     */
    public long dateEnd;

    /**
     * (timestamp in millisecond) The date on which the applicant completed the interview. If the applicant hasn’t completed the interview yet, dateAnswer equals to 0.
     */
    public long dateAnswer;

    /**
     * specify if the applicant have view the invitation mail. It will use only if mail was send to the applicant see applicant creation.
     */
    public boolean emailView;

    /**
     * It indicates if the applicant have clicked the link of the interview in the invitation email. It will apply only if an email was sent to the applicant through the InterviewApp API.
     */
    public boolean linkClicked;

    /**
     * The applicant’s first name.
     */
    public String firstname;

    /**
     * The applicant’s last name.
     */
    public String lastname;

    /**
     * The language chosen for the interview.
     */
    public String lang;

    /**
     * The introduction video displayed before the interview.
     */
    public String videoLink;

    /**
     * The short description shown before the interview.
     */
    public String text;

    /**
     * It indicates if the applicant has been deleted.
     */
    public boolean deleted;

    private static final Set<Integer> acceptedValue = new HashSet<Integer>(Arrays.asList(
            new Integer[] {60, 120, 180, 240, 300}
    ));

    /**
     * This method creates an interview. If successful, it returns the new applicant. There are two ways for creating an applicant.
     * You can either specify an interview which the applicant will have to take or send a list of questions directly.
     * If you choose this second option, an interview will be generated automatically using this list of questions and will be associated to the applicant.
     * @param param see param on doc at http://api.itwapp.io/#create-an-applicant
     * @return the created applicant
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Applicant create(Map<String, Object> param) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {

        if(!param.containsKey("mail") || !param.containsKey("alert") || !param.containsKey("lang") || !param.containsKey("deadline"))  {
            String missing = "missing param: ";

            missing += (!param.containsKey("mail")) ? "mail ": "";
            missing += (!param.containsKey("alert")) ? "alert ": "";
            missing += (!param.containsKey("lang")) ? "lang ": "";
            missing += (!param.containsKey("deadline")) ? "deadline ": "";

            throw new InvalidRequestError(missing);
        }

        if(param.containsKey("questions") && param.get("questions") instanceof List && ((List)param.get("questions")).size() != 0)  {

            List<Map<String, Object>> questions = (List<Map<String, Object>>) param.get("questions");
            for(Map<String, Object> question : questions)   {

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
        }else if(!param.containsKey("interview"))   {
            throw new InvalidRequestError("missing param: interview and questions");
        }

        String json = ApiRequest.post("/api/v1/applicant/", param);

        Gson gson = new Gson();
        return gson.fromJson(json, Applicant.class);
    }

    /**
     * This method deletes a specific applicant.
     * @param applicantId The ID of the applicant to delete
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static void delete(String applicantId) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        ApiRequest.delete("/api/v1/applicant/" +  applicantId);
    }

    /**
     * This method retrieves a specific applicant.
     * @param applicantId The ID of the applicant to retrieve
     * @return the applicant
     * @throws UnauthorizedException
     * @throws InvalidRequestError
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * @throws APIException
     */
    public static Applicant findOne(String applicantId) throws UnauthorizedException, InvalidRequestError, ResourceNotFoundException, ServiceException, APIException {
        String json = ApiRequest.get("/api/v1/applicant/" + applicantId);
        Gson gson = new Gson();
        return gson.fromJson(json, Applicant.class);
    }
}
