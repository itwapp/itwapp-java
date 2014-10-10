package io.itwapp.models;

import io.itwapp.Itwapp;
import io.itwapp.exception.InvalidRequestError;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InterviewTest {

    private static int countInterview = 0;
    private static String interviewId = null;

    @BeforeClass
    public static void setUpBeforeClass()  {
        Itwapp.apiKey = System.getenv("itwappApiKey");
        Itwapp.secretKey = System.getenv("itwappApiSecret");
    }

    @Test
    public void a_testFindAll()    {
        Interview[] res = Interview.findAll(new HashMap<String, Object>());
        InterviewTest.countInterview =  res.length;
    }

    @Test(expected = InvalidRequestError.class)
    public void b_testWrongCreationMissingNameParam() {

        Map<String, Object> param = new HashMap<String, Object>();

        //param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);

    }

    @Test(expected = InvalidRequestError.class)
    public void c_testWrongCreationMissingVideoParam() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        //param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void d_testWrongCreationMissingTextParam() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        //param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void e_testWrongCreationMissingQuestionParam() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void f_testWrongCreationWrongQuestionParam() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        param.put("questions", "RTFM ??!!!!");

        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void g_testWrongCreationMissingParamNumberOnQuestion() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        //question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void h_testWrongCreationMissingParamContentOnQuestion() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        //question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void i_testWrongCreationWrongParamReadingTimeOnQuestion() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 72);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void j_testWrongCreationWrongParamAnswerTimeOnQuestion() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 84);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void k_testWrongCreationWithEmptyQuestion() {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

        param.put("questions", questions);


        Interview.create(param);
    }

    @Test
    public void l_testCreate()    {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);


        Interview interview = Interview.create(param);

        assertEquals(1, interview.questions.length);
        InterviewTest.interviewId = interview.id;
    }

    @Test
    public void m_testFindAllAfterCreate()    {
        Interview[] interviews = Interview.findAll(new HashMap<String, Object>());
        
        assertEquals(InterviewTest.countInterview + 1, interviews.length);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("next", interviews[interviews.length-1].id);
        
        interviews = Interview.findAll(param);
        assertEquals(0, interviews.length);
    }

    @Test
    public void n_testFindOne()   {
        assertNotNull(InterviewTest.interviewId);

        Interview res = Interview.findOne(InterviewTest.interviewId);

        assertEquals("interview 1", res.name);
        assertEquals("", res.video);
        assertEquals("", res.text);
        assertEquals(1, res.questions.length);
        assertEquals("question 1", res.questions[0].content);

    }

    @Test
    public void o_testFindAllApplicant()  {
        assertNotNull(InterviewTest.interviewId);

        Applicant[] applicants = Interview.findAllApplicant(InterviewTest.interviewId, new HashMap<String, Object>());
        assertEquals(0, applicants.length);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingNameParam() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        //param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingVideoParam() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        //param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingTextParam() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        //param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingQuestionParam() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateWrongQuestionParam() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        param.put("questions", "blablablabla");

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingParamNumberOnQuestion() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        //question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateMissingParamContentOnQuestion() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        //question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateWrongParamReadingTimeOnQuestion() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 72);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateWrongParamAnswerTimeOnQuestion() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 84);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void p_testWrongUpdateWithEmptyQuestion() {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

        param.put("questions", questions);

        Interview.update(InterviewTest.interviewId, param);
    }

    @Test
    public void q_testUpdate()    {
        assertNotNull(InterviewTest.interviewId);
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1 - Updated");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        Map<String, Object> question2 = new HashMap<String, Object>();

        question2.put("content", "question 2");
        question2.put("readingTime", 60);
        question2.put("answerTime", 60);
        question2.put("number", 2);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);
        questions.add(question2);

        param.put("questions", questions);

        Interview interview = Interview.update(InterviewTest.interviewId, param);

        assertEquals(2, interview.questions.length);
        assertEquals("question 1 - Updated", interview.questions[0].content);

    }

    @Test
    public void r_testDelete()    {
        assertNotNull(InterviewTest.interviewId);
        Interview.delete(InterviewTest.interviewId, new HashMap<String, Object>());
    }

    @Test
    public void s_testFindAllAfterDelete()    {
        Interview[] interviews = Interview.findAll(new HashMap<String, Object>());

        assertEquals(InterviewTest.countInterview, interviews.length);
    }

}
