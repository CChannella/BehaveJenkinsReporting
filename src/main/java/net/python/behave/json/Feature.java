package net.python.behave.json;

import net.python.behave.ReportBuilder;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import net.python.behave.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Feature {


    private String[] description;

    private Element[] elements;
    private String keyword;

    private String uri;
    private String name;

    //private Tag tags;
    //private ArrayList<String> tags;
    private String status;
    private StepResults stepResults;
    private ScenarioResults scenarioResults;
    private String jsonFile = "";

    public Feature() {

    }

    public String getDeviceName(){
        String name = "";
        if(jsonFile.split("_").length>1)
          name = (jsonFile.split("_")[0]).substring(0,jsonFile.split("_")[0].length());
        System.out.println("name:getDeviceName");
      return name;
    }

    public void setJsonFile(String json){
        this.jsonFile = json;
        System.out.println("name:gsetJsonFile");
    }

    public Sequence<Element> getElements() {
        return Sequences.sequence(elements).realise();

    }

    public String getFileName() {
        List<String> matches = new ArrayList<String>();
        for (String line : Splitter.onPattern("/|\\\\").split(name)) {
            String modified = line.replaceAll("\\)|\\(", "");
            modified = StringUtils.deleteWhitespace(modified).trim();
            matches.add(modified);
            System.out.println("name:getFilename");
        }

        List<String> sublist = matches.subList(1, matches.size());

        matches = (sublist.size() == 0) ? matches : sublist;
        String fileName = Joiner.on("-").join(matches); 

        //If we spect to have parallel executions, we add 
        if(ReportBuilder.isParallel() && jsonFile!=""){
            if(jsonFile.split("_").length >1)
                fileName = fileName + "-"+ (jsonFile.split("_")[0]).substring(0,jsonFile.split("_")[0].length());
        }
        fileName = fileName + ".html";
        return fileName;
    }

   public String getUri(){
        return this.uri;
    }

  /*  public boolean hasTags() {
        return Util.itemExists(tags);
    }*/

    public String[] getTagList() {

    String[] Taglist = {"1","2"};
   return Taglist;
    }
  /*public Sequence<String> getTagList() {
        return getTags().map(Tag.functions.getName());
    }

    public Sequence<Tag> getTags() {
        //return Sequences.sequence(tags).realise();

    }*/

    public String getTagsList() {
        String result = "<div class=\"feature-tags\"></div>";
        /*if (Util.itemExists(tags)) {
            String tagList = StringUtils.join(getTagList(), ",");
            //String tagList = tags[0];
            result = "<div class=\"feature-tags\">" + tagList + "</div>";
        }*/
        return result;
    }

    public Util.Status getStatus() {
        Sequence<Util.Status> results = getElements().map(Element.functions.status());
        //Sequence<Util.Status> results = getElements().map(Element.functions.status());
        if (this.status == "passed")

        {return Util.Status.PASSED;}
        else
        {
            return Util.Status.FAILED;
        }
    }

    public String getName() {
        return Util.itemExists(name) ? Util.result(getStatus()) + "<div class=\"feature-line\"><span class=\"feature-keyword\">" + keyword + ":</span> " + name + "</div>" + Util.closeDiv() : "";
    }

    public String getRawName() {
        return Util.itemExists(name) ? name : "";
    }

    public String getRawStatus() {
        return getStatus().toString().toLowerCase();
    }

    public String getDescription() {
        String result = "";
        if (Util.itemExists(description)) {
            //String content = description.replaceFirst("As an", "<span class=\"feature-role\">As an</span>");
           // String content = description.replaceFirst("As an", "<span class=\"feature-role\">As an</span>");
            String content = description[0];
            System.out.println(content);
            content =  "<span class=\"feature-action\">"+description[0]+"</span>";
            content = content+ "<span class=\"feature-action\">"+description[1]+"</span>";
            content = content+ "<span class=\"feature-value\">"+description[2]+"</span>";
            content = content.replaceAll("\n", "<br/>");
            result = "<div class=\"feature-description\">" + content + "</div>";
        }
        return result;
    }

    public int getNumberOfScenarios() {
        int result = 0;
        if (Util.itemExists(elements)) {
            List<Element> elementList = new ArrayList<Element>();
            for (Element element : elements) {
                if (!element.getKeyword().equals("Background")) {
                    elementList.add(element);
                }
            }
            result = elementList.size();
        }
        return result;
    }

    public int getNumberOfSteps() {
        return stepResults.getNumberOfSteps();
    }

    public int getNumberOfPasses() {
        return stepResults.getNumberOfPasses();
    }

    public int getNumberOfFailures() {
        return stepResults.getNumberOfFailures();
    }

    public int getNumberOfPending() {
        return stepResults.getNumberOfPending();
    }

    public int getNumberOfSkipped() {
        return stepResults.getNumberOfSkipped();
    }

    public int getNumberOfMissing() {
        return stepResults.getNumberOfMissing();
    }

    public int getNumberOfUndefined() {
        return stepResults.getNumberOfUndefined();
    }

    public String getDurationOfSteps() {
        return stepResults.getTotalDurationAsString();
    }

    public int getNumberOfScenariosPassed() {
        return scenarioResults.getNumberOfScenariosPassed();
    }

    public int getNumberOfScenariosFailed() {
        return scenarioResults.getNumberOfScenariosFailed();
    }

    public void processSteps() {
        List<Step> allSteps = new ArrayList<Step>();
        List<Step> passedSteps = new ArrayList<Step>();
        List<Step> failedSteps = new ArrayList<Step>();
        List<Step> skippedSteps = new ArrayList<Step>();
        List<Step> undefinedSteps = new ArrayList<Step>();
        List<Step> pendingSteps = new ArrayList<Step>();
        List<Step> missingSteps = new ArrayList<Step>();
        List<Element> passedScenarios = new ArrayList<Element>();
        List<Element> failedScenarios = new ArrayList<Element>();
        double totalDuration = 0l;
        if (Util.itemExists(elements)) {
            for (Element element : elements) {
                calculateScenarioStats(passedScenarios, failedScenarios, element);
                if (Util.hasSteps(element)) {
                    Sequence<Step> steps = element.getSteps();
                    for (Step step : steps) {
                        allSteps.add(step);
                        Util.Status stepStatus = step.getStatus();
                        passedSteps = Util.setStepStatus(passedSteps, step, stepStatus, Util.Status.PASSED);
                        failedSteps = Util.setStepStatus(failedSteps, step, stepStatus, Util.Status.FAILED);
                        skippedSteps = Util.setStepStatus(skippedSteps, step, stepStatus, Util.Status.SKIPPED);
                        undefinedSteps = Util.setStepStatus(undefinedSteps, step, stepStatus, Util.Status.UNDEFINED);
                        pendingSteps = Util.setStepStatus(pendingSteps, step, stepStatus, Util.Status.PENDING);
                        missingSteps = Util.setStepStatus(missingSteps, step, stepStatus, Util.Status.MISSING);
                        totalDuration = totalDuration + step.getDuration();
                    }
                }
            }
        }
        scenarioResults = new ScenarioResults(passedScenarios, failedScenarios);
        stepResults = new StepResults(allSteps, passedSteps, failedSteps, skippedSteps, pendingSteps, missingSteps, undefinedSteps, totalDuration);
    }

    private void calculateScenarioStats(List<Element> passedScenarios, List<Element> failedScenarios, Element element) {
        if (!element.getKeyword().equals("Background")) {
            if (element.getStatus() == Util.Status.PASSED) {
                passedScenarios.add(element);
            } else if (element.getStatus() == Util.Status.FAILED) {
                failedScenarios.add(element);
            }
        }
    }

    class StepResults {

        List<Step> allSteps;
        List<Step> passedSteps;
        List<Step> failedSteps;
        List<Step> skippedSteps;
        List<Step> undefinedSteps;
        List<Step> pendingSteps;
        List<Step> missingSteps;
        Double totalDuration;

        public StepResults(List<Step> allSteps, List<Step> passedSteps, List<Step> failedSteps, List<Step> skippedSteps, List<Step> pendingSteps, List<Step> missingSteps, List<Step> undefinedSteps, Double totalDuration) {
            this.allSteps = allSteps;
            this.passedSteps = passedSteps;
            this.failedSteps = failedSteps;
            this.skippedSteps = skippedSteps;
            this.undefinedSteps = undefinedSteps;
            this.pendingSteps = pendingSteps;
            this.missingSteps = missingSteps;
            this.totalDuration = totalDuration;
        }

        public int getNumberOfSteps() {
            return allSteps.size();
        }

        public int getNumberOfPasses() {
            return passedSteps.size();
        }

        public int getNumberOfFailures() {
            return failedSteps.size();
        }

        public int getNumberOfUndefined() {
            return undefinedSteps.size();
        }

        public int getNumberOfPending() {
            return pendingSteps.size();
        }

        public int getNumberOfSkipped() {
            return skippedSteps.size();
        }

        public int getNumberOfMissing() {
            return missingSteps.size();
        }

        public double getTotalDuration() {
            return totalDuration;
        }

        public String getTotalDurationAsString() {
            return Util.formatDuration(totalDuration);
        }
    }

    class ScenarioResults {
        List<Element> passedScenarios;
        List<Element> failedScenarios;

        ScenarioResults(List<Element> passedScenarios, List<Element> failedScenarios) {
            this.passedScenarios = passedScenarios;
            this.failedScenarios = failedScenarios;
        }

        public int getNumberOfScenariosPassed() {
            return passedScenarios.size();
        }

        public int getNumberOfScenariosFailed() {
            return failedScenarios.size();
        }

    }


}
