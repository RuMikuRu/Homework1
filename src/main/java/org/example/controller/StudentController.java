package org.example.controller;

import org.example.model.Person;
import org.example.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet(urlPatterns = {"/student"})
public class StudentController extends HttpServlet {
    private final StudentService service = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String reqStr = new String(req.getInputStream().readAllBytes());
        var request = mapper.readValue(reqStr, Person.class);
        if (!service.changePersonGrade(request))
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String groupId = request.getParameter("groupId");
        int group = Integer.parseInt(groupId);
        ObjectMapper mapper = new ObjectMapper();
        var arr = service.calculateAverageGradeByClass(group);
        mapper.writeValue(out, arr);
    }
}
