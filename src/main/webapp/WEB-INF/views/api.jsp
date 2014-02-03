<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 


<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="format-detection" content="telephone=no"/>
    <title>API Test</title>
    <script src="${ctx}/static/js/jquery-1.7.2.js"></script>
    <style>
      input[type=text] {
        width: 500px;
        height: 30px;
        line-height: 30px;
      }
      textarea {
        width: 500px;
        height: 150px;
      }
    </style>
  </head>

  <body>
    <div>
      <p>URL: </p>
      <input type="text" id="url" value="http://localhost:8080/bbks/" /><br />
    </div>

    <div>
      <p>Method: </p>
      <label><input checked="checked" name="request_type" type="radio" value="get">GET</label>
      <label><input name="request_type" type="radio" value="post">POST </label>
      <label><input name="request_type" type="radio" value="put">PUT </label>
      <label><input name="request_type" type="radio" value="delete">DELETE</label>
    </div>

    <div style="display:-webkit-box;">
      <div style="box-flex:1.0;">
        <p>Params: </p>
        <textarea id="params"></textarea>
      </div>

      <div style="box-flex:1.0;">
        <p>Result: </p>
        <textarea id="result"></textarea>
      </div>
    </div>

    <div>
      <button>Submit</button>
    </div>

    <script>
      $('button').click(function() {
        var url = $('#url').val();
        var params = $('#params').val().split('\n');
        var data = {};
        var method;

        $('input[name=request_type]').each(function(i, input) {
          if (input.checked) {
            method = input.value;
          }
        });

        params.forEach(function(fields, index, arr) {
          if (!fields) {
            return;
          }

          fields = fields.split('=');

          if (/^[\{|\[]/.test(fields[1])) {
            data[fields[0]] = JSON.parse(fields[1]);
          }
          else {
            data[fields[0]] = fields[1];
          }
        });

        console.log('send ajax: ', url, method, data);

                $('#result').val('');

        $.ajax({
          url: url,
          type: method,
          data: data,
          headers: {
            'apikey': $('#apikey').val()
          },
          success: function(result) {
            $('#result').val(JSON.stringify(result));
            console.log(this, result);
          },
          error: function(err) {
            $('#result').val(err.responseText);
          }
        })
      });
    </script>
  </body>
</html>
