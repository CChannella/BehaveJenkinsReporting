Publish pretty Html reports on Jenkins:

This is a Python Jenkins plugin which publishes pretty html reports showing the results of Behave runs.

Background:

Behave is a test automation tool following the principles of BDD Specifications are written in a concise human readable form and executed in continuous integration.

This plugin allows Jenkins to publish the results as y html reports hosted by the Jenkins build server. In order for this plugin to work you must be generating a json report using behave --format=json.pretty --outfile=resultsfilenameofyourchoice.json.

The plugin converts the json report into an overview html linking to separate feature file htmls with stats and results.

Many thanks to Kingsley Hendriks for the original plugin, this is a modified version of Cucumber HTML reports plugin developed for Cucumber JVM
