# quotes
quotes is a small and very simple AngularJS RESTful application with a Couchbase backend.<br> Credits to draptik for his angularrestful ngdemo which was very helpful during the creation of this project.

Notes<br>
* CRUD - So far no Delete, and Create/Update is the same function (handled by Couchbase backend).
* Couchbase - I use the default bucket and cluster all the time.<br>
* Views - the view "name_and_quote" looks like this:<br>
<code> function (doc, meta) {
  if (doc.name && doc.quote) {
    emit(doc.name, doc.quote);
  }}
</code>

