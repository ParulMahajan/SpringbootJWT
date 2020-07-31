package com.allianz.ins.cucumber.controller;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature",
        
        extraGlue = "com.allianz.ins.cucumber.stepDefinitions")
public class EmployeeController {

}
