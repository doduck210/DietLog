//global variable
var apiData;

function init(){
    //get cureent date
    const timeElapsed = Date.now();
    const today = new Date(timeElapsed);
    var month=String(today.getMonth()+1);
    if (month.length<2) month="0"+month;
    var day=String(today.getDate());
    if (day.length<2) day="0"+day;
    var curDate=String(today.getFullYear())+"-"+month+"-"+day;
    
    document.getElementById("date").value = curDate;
    showMenu(curDate);
  }

function showMenu(date){
    //clear the list
    const items = document.getElementById('menuList').getElementsByTagName('li')
    while(items.length>0) items[0].remove();
    //get data using api
    fetch("http://localhost:8080/api/diet/get/day?date="+date)
      .then((response) => response.json())
      .then((data) => { console.log(data);
          apiData=data;
          if(data.length>0){
            for(var i=0;i<data.length;i++){

              //BR/LU 등 한글표기로 바꿔주기
              var apiTime = data[i]["time"];
              if(apiTime=="BR") apiTime="아침";
              else if(apiTime=="LU") apiTime="점심";
              else if(apiTime=="DI") apiTime="저녁";
              else if(apiTime=="NI") apiTime="야식";
              else if(apiTime=="EX") apiTime="기타";
              var apiMenu= data[i]["menu"];

              //filling up the list
              const li = document.createElement("li"); 
              li.setAttribute('id',"lis");
              const textNode = document.createTextNode(apiTime+" : " + apiMenu+" ");              
              li.appendChild(textNode);
              //removeButton
              const bu = document.createElement("button");
              bu.setAttribute('id',"removeButton");
              bu.setAttribute('value',i);
              bu.setAttribute('onclick',"removeMenu("+i+")");
              const buText = document.createTextNode("삭제");
              bu.appendChild(buText);
              li.appendChild(bu);
              document.getElementById('menuList').appendChild(li);

              }
            }
          //if there's no data
          else{
            const li = document.createElement("li"); 
            li.setAttribute('id',"lis");
            const textNode = document.createTextNode(date+" 날의 식사기록이 없습니다.");
            li.appendChild(textNode);
            document.getElementById('menuList').appendChild(li);
          }
        }
    );
}

function createMenu(){
    var date = document.getElementById("date").value;
    var time = document.getElementById("registerTime").value;
    var menu = document.getElementById("registerMenu").value;
    if(menu.length<1) {
        alert("메뉴를 입력해주세요");
        return;
    }
    if(confirm(date+"일에 "+menu+"를 등록하시겠습니까?")){
        //register menu using api
        fetch("http://localhost:8080/api/diet/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            "date" : date,
            "time" : time,
            "menu" : menu
        })
        }).then((response) => {
            console.log(response);
            alert("등록완료");
            showMenu(date);
        });
    }
}

function removeMenu(index){
    var date = document.getElementById("date").value;
    var time = apiData[index]["time"];
    var menu = apiData[index]["menu"];
    console.log(date,time,menu);

    if(confirm(date+"일에 "+menu+"를 삭제하시겠습니까?")){
    //delete menu using api
    fetch("http://localhost:8080/api/diet/delete", {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            "date" : date,
            "time" : time,
            "menu" : menu
        })
        }).then((response) => {
            console.log(response);
            alert("삭제완료");
            showMenu(date);
        });
    }
}