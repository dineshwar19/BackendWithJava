var convert = require('xml-js');
var fs = require('fs');


var xml = '<bug> ' +
'<bug_id>151716</bug_id>'+
'<creation_ts>2022-05-19 13:56:46 +0200</creation_ts>'+
'<short_desc>About box changes due to MDR transition</short_desc>'+
'<delta_ts>2022-10-14 13:42:50 +0200</delta_ts>'+
'<reporter_accessible>1</reporter_accessible>'+
'<cclist_accessible>1</cclist_accessible>'+
'<classification_id>27</classification_id>'+
'<classification>S&nbsp;nbsp;N Transition</classification>'+
'<product>Hip7</product>'+
'<component>GUI</component>'+
'<version>7.0.1 development</version>'+
'<rep_platform>All</rep_platform>'+
'<op_sys>All</op_sys>'+
'<bug_status>RESOLVED</bug_status>'+
'<resolution>FIXED</resolution>'+
'<bug_file_loc />'+
'<status_whiteboard />'+
'<keywords />'+
'<priority>P5</priority>'+
'</bug>';

var xml1 = '<bug>'+
'<bug_id>151716</bug_id>'+
'<creation_ts>2022-05-19 13:56:46 +0200</creation_ts>'+
'<short_desc>About box changes due to MDR transition</short_desc>'+
'<delta_ts>2022-10-14 13:42:50 +0200</delta_ts>'+
'<reporter_accessible>1</reporter_accessible>'+
'<cclist_accessible>1</cclist_accessible>'+
'<classification_id>27</classification_id>'+
'<classification>S&nbsp;N Transition</classification>'+
'<product>Hip7</product>'+
'<component>GUI</component>'+
'<version>7.0.1 development</version>'+
'<rep_platform>All</rep_platform>'+
'<op_sys>All</op_sys>'+
'<bug_status>RESOLVED</bug_status>'+
'<resolution>FIXED</resolution>'+
'<bug_file_loc />'+
'<status_whiteboard />'+
'<keywords />'+
'<priority>P5</priority>'+
'<bug_severity>undefined</bug_severity>'+
'<target_milestone>7.1.0</target_milestone>'+
'<everconfirmed>1</everconfirmed>'+
'<reporter name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</reporter>'+
'<assigned_to name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</assigned_to>'+
'<cc>mario.schubert.ext@brainlab.com</cc>'+
'<estimated_time>0.00</estimated_time>'+
'<remaining_time>0.00</remaining_time>'+
'<actual_time>0.00</actual_time>'+
'<cf_specification />'+
'<cf_test />'+
'<cf_task_or_issue>Bug</cf_task_or_issue>'+
'<cf_loka_analysis />'+
'<cf_ots />'+
'<cf_visual_id />'+
'<cf_cid />'+
'<token>1675068676-vrsrTqBURfz8JkW8oZueLRq8hGFCNXamUPT7nfhjt9E</token>'+
'<group id="21">S&nbsp;N</group>'+
'<comment_sort_order>oldest_to_newest</comment_sort_order>'+
'<long_desc isprivate="0">'+
'<commentid>817538</commentid>'+
'<comment_count>0</comment_count>'+
'<who name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</who>'+
'<bug_when>2022-05-19 13:56:46 +0200</bug_when>'+
'<thetext>See also bugs 138831 and 150788. Th about box needs to be adapted to fit to MDR'+
'requirements, this includes: - show Medical Device Name - show GTIN (UDI and PI) -'+
'new design - representative swiss</thetext>'+
'</long_desc>'+
'<long_desc isprivate="0">'+
'<commentid>817603</commentid>'+
'<comment_count>1</comment_count>'+
'<who name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</who>'+
'<bug_when>2022-05-19 16:07:33 +0200</bug_when>'+
'<thetext>--> set to confirmed</thetext>'+
'</long_desc>'+
'<long_desc isprivate="0">'+
'<commentid>822753</commentid>'+
'<comment_count>2</comment_count>'+
'<who name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</who>'+
'<bug_when>2022-06-29 14:33:48 +0200</bug_when>'+
'<thetext>changed target milestone to 7.0.1</thetext>'+
'</long_desc>'+
'<long_desc isprivate="0">'+
'<commentid>832404</commentid>'+
'<comment_count>3</comment_count>'+
'<who name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</who>'+
'<bug_when>2022-10-12 15:00:12 +0200</bug_when>'+
'<thetext>--> set status to resolved fixed</thetext>'+
'</long_desc>'+
'<long_desc isprivate="0">'+
'<commentid>832572</commentid>'+
'<comment_count>4</comment_count>'+
'<who name="Franziska Reifenschneider (S&nbsp;N)">reifenschneider.ext@brainlab.com</who>'+
'<bug_when>2022-10-14 13:42:50 +0200</bug_when>'+
'<thetext>Version 7.0.1 development already refers to project SNNA030 for release of HIP7'+
'under MDR which was later changed to a second digit change --> HIP7 v7.1 Target Hip'+
'7.1.0 refers to project SNNA030 for release of HIP7 under MDR and not cancelled'+
'project SNNA06 (Hyperion)</thetext>'+
'</long_desc>'+
'</bug>';

var result = convert.xml2json(xml1, {compact: true, spaces:4});

//console.log(result)
const xmlFile = fs.readFileSync('input.xml', 'utf-8')

const jsonData = convert.xml2json(xmlFile, {compact: true, spaces: 4});

//const writeData = JSON.stringify(jsonData,null,4);

try{
fs.writeFileSync('output.json', jsonData)
console.log("JSON Data is saved;")
} catch (error) {
    console.log(error)
}