<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<!DOCTYPE html>
<HTML>

<HEAD>

    <TITLE>Форма обратной связи</TITLE>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

    <!-- Установка элемента меню !-->
    <!-- --------------------------------------------------------------------------------------------------------- !-->
    <script type="text/javascript">
        function autoPopulate_resultAction_receiver(targetElement) {
            targetElement.form.elements['Receiver'].value = targetElement.options[targetElement.selectedIndex].value;
        }
    </script>

    <!-- Отправка данных серверу !-->
    <!-- --------------------------------------------------------------------------------------------------------- !-->
    <script type="text/javascript">
        function sendData() {
            $.ajax({
                     type: "POST",
                      url: "/mypage",
                     data: $('#formId').serialize(),
                   }).done(function (res) {
                                             alert("send by ajax");
                                             location.reload();
                                          });
        }
    </script>

    <!-- Валидация полей !-->
    <!-- --------------------------------------------------------------------------------------------------------- !-->
    <script type="text/javascript">
        $(document).ready(function() {
            $(".button").click(function(){

                ver = true;
                first_name = $("#FirstName").val();
                second_name = $("#SecondName").val();
                last_name = $("#LastName").val();
                theme = $("#Theme").val();
                message = $("#Message").val();

                var p = /^[а-яА-ЯёЁa-zA-Z0-9 \-]{2,35}$/i;

                if(!p.test(first_name)) {
                   message_a = "Укажите Фамилию";
                   ver = false;
                }
                else if(!p.test(second_name)) {
                        message_a = "Укажите Имя";
                        ver = false;
                     }
                     else if(!p.test(last_name)) {
                             message_a = "Укажите Отчество";
                             ver = false;
                          }
                          else if(!p.test(theme)) {
                                  message_a = "Не указана тема сообщения!";
                                  ver = false;
                               }
                               else if(!p.test(message)) {
                                       message_a = "Укажите текст сообщения";
                                       ver = false;
                                    }
                if(ver) {
                   sendData();
                   return true;
                }
                else {
                        alert(message_a);
                        return false;
                     }
            });
        });
    </script>

</HEAD>

<BODY BGCOLOR = "#fff8dc">

<H1 ALIGN = "CENTER">
    <div style="font-family: 'Calibri Light'">
        Форма обратной связи
    </div>
</H1>

<form ACTION = "/mypage" method = "post" id = "formId">

    <H3 align = "LEFT"><div style = "font-family: 'Calibri Light'">Отправитель:</div></H3>

    <div style = "font-family: 'Calibri Light'">
        <table>
            <tr>
                <td>Фамилия:</td>
                <td><input TYPE="TEXT" id = "FirstName" NAME = "FirstName" required></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <td><input TYPE="TEXT" id = "SecondName" NAME = "SecondName"></td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <td><input TYPE="TEXT" id = "LastName" NAME = "LastName"></td>
            </tr>
        </table>
        <br><hr><br>
        <table>
            <tr>
                <td>Получатель:</td>
                <td>
                    <select onChange="autoPopulate_resultAction_receiver(this);" name = "Receiver">
                        <option value="ivan@mail.ru">Иванов Иван Иванович</option>
                        <option value="petr@mail.ru">Петров Петр Петрович</option>
                        <option value="sidor@mail.ru">Сидоров Сидор Сидорович</option>
                        <option value="alex@mail.ru">Алексеев Алексей Алексеевич</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Тема:</td>
                <td><input TYPE = "TEXT" id = "Theme" NAME = "Theme"></td>
            </tr>
            <tr>
                <td>Текст сообщения:</td>
                <td><textarea NAME = "Message" id = "Message" ROWS = 3 COLS = 40></textarea></td>
            </tr>
        </table>
    </div>

    <br><hr><br>
    <input type = "button" class = "button" value = "Сохранить"/>

</form>

<!-- Чтение уже имеющихся данных из таблицы !-->
<!-- --------------------------------------------------------------------------------------------------------- !-->
<div style = "font-family: 'Calibri Light'">
    <br>
    <table id = "myTable" width="100%">
        <% ResultSet rs = (ResultSet) request.getAttribute("result"); %>
        <tr style="background: silver">
            <td>Фамилия</td>
            <td>Имя</td>
            <td>Отчество</td>
            <td>Отправитель</td>
            <td>Тема</td>
            <td>Сообщение</td>
        </tr>
        <% while(rs.next()) {%>
        <tr style="background: blanchedalmond">
            <td><%= rs.getString(2) %></td>
            <td><%= rs.getString(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td><%= rs.getString(5) %></td>
            <td><%= rs.getString(6) %></td>
            <td><%= rs.getString(7) %></td>
        </tr>
        <%}%>
    </table>
</div>

</BODY>

</HTML>