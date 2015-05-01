
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-62530705-3', 'auto');
  ga('send', 'pageview');

</script>
Publish pretty Html reports on Jenkins:

This is a Python Jenkins plugin which publishes pretty html reports showing the results of Behave runs.

Background:

Behave is a test automation tool following the principles of BDD Specifications are written in a concise human readable form and executed in continuous integration.

This plugin allows Jenkins to publish the results as y html reports hosted by the Jenkins build server. In order for this plugin to work you must be generating a json report using behave --format=json.pretty --outfile=resultsfilenameofyourchoice.json.

The plugin converts the json report into an overview html linking to separate feature file htmls with stats and results.

Many thanks to Kingsley Hendriks for the original plugin, this is a modified version of Cucumber HTML reports plugin developed for Cucumber JVM
