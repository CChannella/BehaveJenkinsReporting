package net.python.behave;

import net.python.behave.json.BehaveFeature;
import net.python.behave.util.Util;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FeatureTest {

    ReportParser reportParser;
    BehaveFeature passingFeature;
    BehaveFeature failingFeature;

    @Before
    public void setUpJsonReports() throws IOException {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(FileReaderUtil.getAbsolutePathFromResource("net/python/behave/cc1.json"));
        reportParser = new ReportParser(jsonReports);
        //passingFeature = reportParser.getFeatures().entrySet().iterator().next().getValue().get(0);
        passingFeature = reportParser.getFeatures().entrySet().iterator().next().getValue().get(0);
        failingFeature = reportParser.getFeatures().entrySet().iterator().next().getValue().get(1);
        passingFeature.processSteps();
        failingFeature.processSteps();
    }

    @Test
    public void shouldReturnManagedFileName() {
        System.out.print(passingFeature.getFileName());
        assertThat(passingFeature.getFileName(), is("CareersHomePage.html"));
    }

    @Test
    public void shouldGetDescription() {
        //assertThat(passingFeature.getDescription(), is("<div class=\"feature-description\">As a Account Holder<br/><span class=\"feature-action\">I want to</span> withdraw cash from an ATM<br/><span class=\"feature-value\">So that</span> I can get money when the bank is closed</div>"
        assertThat(passingFeature.getDescription(), is("<div class=\"feature-description\"><span class=\"feature-action\">In order to join the most amazing company in the world</span><span class=\"feature-action\">As a potential employee</span><span class=\"feature-value\">I want to find the right job for me (i.e. have my head blown off when i look at the site)</span></div>"
        ));
    }

    @Test
    public void shouldKnowIfTagsExists() {
       // assertThat(passingFeature.hasTags(), is(false));
    }

  /*  @Test
    public void shouldListTheTags() {
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("@super");
        assertThat(passingFeature.getTagList().toList(), is(expectedList));
    }*/

    @Test
    public void shouldListTheTagsAsHtml() {
//        assertThat(passingFeature.getTagsList(), is("<div class=\"feature-tags\"></div>"));
    }

    @Test
    public void shouldGetStatus() {
        assertThat(passingFeature.getStatus(), Is.is(Util.Status.PASSED));
    }

    @Test
    public void shouldReturnName() {
        assertThat(passingFeature.getName(), is("<div class=\"passed\"><div class=\"feature-line\"><span class=\"feature-keyword\">Feature:</span> Careers Home Page</div></div>"));
    }

    @Test
    public void shouldReturnRawName() {
        assertThat(passingFeature.getRawName(), is("Careers Home Page"));
    }

    @Test
    public void shouldReturnRawStatus() {
        assertThat(passingFeature.getRawStatus(), is("passed"));
    }

    @Test
    public void shouldGetNumberOfSteps() {
        assertThat(passingFeature.getNumberOfSteps(), is(5));
    }

    @Test
    public void shouldGetNumberOfPassingSteps() {
        assertThat(passingFeature.getNumberOfPasses(), is(5));
//        assertThat(failingFeature.getNumberOfPasses(), is(0));

    }

    @Test
    public void shouldGetNumberOfFailingSteps() {
        assertThat(passingFeature.getNumberOfFailures(), is(0));
        assertThat(failingFeature.getNumberOfFailures(), is(1));
    }

    @Test
    public void shouldGetNumberOfSkippedSteps() {
        assertThat(passingFeature.getNumberOfSkipped(), is(0));
//        assertThat(failingFeature.getNumberOfSkipped(), is(3));
    }

    @Test
    public void shouldGetNumberOfPendingSteps() {
        assertThat(passingFeature.getNumberOfPending(), is(0));
    }

    @Test
    public void shouldGetNumberOfMissingSteps() {
        assertThat(passingFeature.getNumberOfMissing(), is(0));
    }

    @Test
    public void shouldGetDurationOfSteps() {
        assertThat(passingFeature.getDurationOfSteps(), StringContains.containsString("ms"));
    }

    @Test
    public void shouldGetNumberOScenarios() {
        assertThat(passingFeature.getNumberOfScenarios(), is(1));
    }

    @Test
    public void shouldProcessFeatureWhenNoScenarios() throws IOException {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(FileReaderUtil.getAbsolutePathFromResource("net/python/behave/noscenario.json"));
        ReportParser reportParser = new ReportParser(jsonReports);
      //  BehaveFeature feature = reportParser.getFeatures().entrySet().iterator().next().getValue().get(0);
      //  feature.processSteps();
    }

    @Test
    public void shouldGetNumberOfPassingScenarios() {
        assertThat(passingFeature.getNumberOfScenariosPassed(), is(1));
    }

    @Test
    public void shouldGetNumberOfFailingScenarios() {
        assertThat(failingFeature.getNumberOfScenariosFailed(), is(1));
    }

}