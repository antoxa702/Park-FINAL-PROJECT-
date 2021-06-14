<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<script>
    function openList(evt, employeeName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for(i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for(i = 0; i < tablinks.length; i++) {
            tablinks.className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(employeeName).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>

<script>
    document.getElementById("defaultOpen").click();
</script>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {font-family: Arial;}

        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        .tab button:hover {
            background-color: #ddd;
        }

        .tab button.active {
            background-color: #ccc;
        }

        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>
</head>
<body>
<div class="tab">
    <button class="tablinks" onclick="openList(event, 'Anton')" id="defaultOpen">Anton</button>
    <button class="tablinks" onclick="openList(event, 'Irina')">Irina</button>
    <button class="tablinks" onclick="openList(event, 'Stas')">Stas</button>
    <button class="tablinks" onclick="openList(event, 'Renata')">Renata</button>
</div>

<div id="Anton" class="tabcontent">
    <h3>Anton's tab</h3>
    <p>Anton's list here</p>
</div>

<div id="Irina" class="tabcontent">
    <h3>Irina's tab</h3>
    <p>Irina's list here</p>
</div>

<div id="Stas" class="tabcontent">
    <h3>Stas's tab</h3>
    <p>Stas's list here</p>
</div>

<div id="Renata" class="tabcontent">
    <h3>Renata's tab</h3>
    <p>Renata's list here</p>
</div>
</body>

</html>
