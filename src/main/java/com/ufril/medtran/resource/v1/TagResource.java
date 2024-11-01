package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.helper.MessageHelper;
import com.ufril.medtran.helper.ResourceValidationHelper;
import com.ufril.medtran.persistence.domain.common.Tag;
import com.ufril.medtran.persistence.service.EmployeeService;
import com.ufril.medtran.persistence.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "tagResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "tag")
public class TagResource {

    @Autowired
    private TagService tagService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/tag/getAllTags/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTags(@PathVariable("companyId") Integer companyId) {
        List<Tag> tags = tagService.getAllTags(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, tags), HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/getTagById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTagById(@PathVariable("id") final long id) {
        Tag tag = tagService.getTagById(id);
        return new ResponseEntity<>(new Response(StatusType.OK, tag), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Tag",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Tag", response = Response.class)
    })
    @RequestMapping(
            value = "/tag/createTag",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createTag(@RequestBody Tag tag) {
        long id = tagService.createTag(tag);
        return new ResponseEntity<>(new Response(StatusType.OK, id), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update existing Tag",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Tag", response = Response.class)
    })
    @RequestMapping(
            value = "/tag/updateTag",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateTag(@RequestBody Tag tag) {
        long id = tagService.upDateTag(tag);
        return new ResponseEntity<>(new Response(StatusType.OK, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(new Response(StatusType.OK, "TEST"), HttpStatus.OK);
    }
}
