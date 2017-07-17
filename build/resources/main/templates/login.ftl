<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css">

</head>
<body>

<form method="post" enctype="multipart/form-data">
    <input type="file" name="uploaded_file">
    <button>Upload picture</button>
</form>



<#if images??>
    <#list images as image>
        <img src="data:image/png;base64,${image.getImgBase64()}">
    </#list>
</#if>

<script src="/js/vendor/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>