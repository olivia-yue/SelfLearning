package com.olivia.bdd.function.testsuites;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {
        "src/test/resources/features/function/homepage_login.feature" }, glue = {
                "com.olivia.bdd.function.steps" })
public class HomePageLoginTestSuite {

}
