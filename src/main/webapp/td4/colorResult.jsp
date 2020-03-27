<%--
  Created by IntelliJ IDEA.
  User: Trombonesolo
  Date: 24/03/2020
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="colors" class="fr.polytech.jydet.td4.beans.ColorBean" scope="request">
    <jsp:setProperty name="colors" property="backColor"/>
    <jsp:setProperty name="colors" property="fontColor"/>
</jsp:useBean>

<html>
<head>
    <title>ColorResult</title>
</head>
<body style="width: 50%;margin: auto; padding-top: 100px">
<div id="lipsum">

    <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tincidunt id leo pellentesque lacinia. Suspendisse ultricies ultricies tortor nec accumsan. Integer gravida pharetra est, nec gravida diam congue quis. Duis nec tortor quis nisl accumsan imperdiet at ac eros. Pellentesque lobortis mollis dui, non euismod nibh pellentesque in. Duis facilisis euismod lorem quis gravida. Aliquam erat volutpat. Nam mi mauris, congue at aliquet et, mattis vitae quam. Nullam eu semper libero. Proin imperdiet lobortis magna, ac imperdiet dolor pretium at. Etiam non quam a erat tempus facilisis.
    </p>
    <p>
        Integer molestie quis nulla ac posuere. Nunc iaculis sapien justo, nec maximus mi interdum vel. Donec eget mollis nisi. Ut ut laoreet nisl, placerat scelerisque mi. Vivamus iaculis ligula efficitur, malesuada quam vel, dapibus nisl. Sed in orci vel nisi mollis dignissim. In volutpat justo vel cursus rhoncus. Maecenas lacinia lobortis mauris vitae mollis. Sed in tortor tellus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec est dolor, tristique eget placerat vel, tincidunt ac diam. Phasellus feugiat interdum scelerisque. Integer in dolor non nulla ultricies semper. Mauris consequat ligula vel ullamcorper dignissim.
    </p>
    <p>
        Curabitur dui ipsum, sagittis in convallis eu, sagittis a sem. Cras at ante accumsan, tempus turpis convallis, facilisis magna. Nam id interdum risus, eu consectetur velit. Cras venenatis diam libero, ut convallis nibh laoreet iaculis. Aliquam porttitor est metus, sit amet tincidunt mi fermentum quis. Fusce rhoncus fringilla risus, in porta augue varius a. Duis mollis semper dapibus. Nulla molestie non nisl vitae tincidunt. Morbi sagittis gravida tortor in congue. Praesent et felis euismod, sodales nunc nec, aliquet nibh. Pellentesque a eros odio.
    </p>
    <p>
        Maecenas tempus varius ex id maximus. Quisque congue, ex a mattis ornare, ante urna vulputate nisi, sed cursus purus mauris ut nulla. Proin egestas velit dolor, a lacinia sem molestie a. Vestibulum at magna feugiat lacus finibus vestibulum. Quisque nec ullamcorper risus. Duis nec interdum neque. Ut sodales augue id nisi eleifend tempus. Curabitur et ullamcorper nunc, a aliquet orci. Fusce gravida, ex et condimentum sodales, ligula elit fermentum nulla, vitae euismod urna metus eu felis. Fusce viverra lobortis feugiat. Curabitur et varius lorem.
    </p>
    <p>
        Phasellus id eleifend lectus. Phasellus et felis nec quam semper gravida quis et nibh. In hac habitasse platea dictumst. Pellentesque nec metus ut mi lacinia commodo sed vel sem. Donec sodales gravida euismod. Maecenas viverra mi velit, sit amet rhoncus enim vehicula efficitur. Maecenas maximus vitae est sit amet luctus. Aliquam pulvinar elit in nunc hendrerit congue. Sed ligula lorem, porta pellentesque dui vestibulum, pulvinar bibendum magna.
    </p></div>
</body>
<style> html {
    background-color: <%=colors.getBackColor()%>;
    color: <%=colors.getFontColor()%>;
} </style>
</html>
