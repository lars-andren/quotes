/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angular.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.angular.domain.Employee;
import com.angular.domain.Quote;
import com.google.gson.Gson;
import javax.ws.rs.QueryParam;


@Path("/employee")
public class EmployeeService {

    private final static List<Employee> reporteesList = new ArrayList<Employee>();

    static {
        reporteesList.add(new Employee(100, "Mike", "Ryan"));
        reporteesList.add(new Employee(101, "Michael", "Holding"));
    }

    /**
     * Returns employee Details.
     */
    @GET
    //@Path("/{param}")
    //public Response getEmployeeById(@PathParam("param") String employeeID) {
    public Response getEmployeeById() {
        Gson gson = new Gson();
        String output = gson.toJson(new Employee(101, "Antony", "Wayne"));

        output = gson.toJson((new Quote(1, "ello", "luv")));

        System.out.println("_____REACHED SERVICE");

        return Response.status(200).entity(output).build();
    }

    
    /**
     * Returns the reportees of an Employee;
     */
    @GET
    @Path("/{param}/reportees")
    public Response findReportees(@PathParam("param") String empID, @QueryParam("admin") boolean admin) {
        Gson gson = new Gson();
        String output = gson.toJson(reporteesList);
        System.out.println(" id == " + admin);
        if (!admin) {
         return Response.status(200).entity(output).build();
        }else 
        {
            return Response.status(200).entity("[{ \"number\": \"1234\"}, { \"number\": \"56789\"}]").build();
        }
    }

}
