package com.eriegarbage.garbageapp.controllers;

import com.eriegarbage.garbageapp.dto.ComplaintResponseDto;
import com.eriegarbage.garbageapp.managers.ComplaintManager;
import com.eriegarbage.garbageapp.models.Complaint;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComplaintController {

    @Autowired
    ComplaintManager complaintManager;

    @RequestMapping(value = "/fileComplaintPage")
    public ModelAndView getComplaintPage() {
        ModelAndView mv = new ModelAndView("ComplaintPage");
        // mv.addObject("accountInfo", "info");
        mv.addObject("complaints", complaintManager.readComplaints());
        return mv;
    }

    @RequestMapping(value = "/viewComplaintPage")
    public ModelAndView getViewComplaintsPage() {
        ModelAndView mv = new ModelAndView("ViewComplaintsPage");
        mv.addObject("complaints", complaintManager.readComplaints());
        return mv;
    }

    @RequestMapping(value = "submitComplaint", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void submitComplaint(@RequestParam("input") String complaintString) {
        Complaint complaint = new Complaint(complaintString);
        complaintManager.submitComplaint(complaint);
    }

    @RequestMapping(value = "/viewComplaintsTest")
    @ResponseBody
    public List<Complaint> viewComplaintsTest() {
        return complaintManager.readComplaints();
    }

    @RequestMapping(value = "markComplaintAsViewed", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void markComplaintAsViewed(@RequestParam("id") int id) {
        complaintManager.markComplaintAsViewed(id);
    }

    @RequestMapping(value = "respondToComplaint", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void respondToComplaint(@RequestBody ComplaintResponseDto response) {
        complaintManager.respondToComplaint(response.getId(), response.getResponse());
    }
}
