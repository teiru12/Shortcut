<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>숏컷</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
</head>
<body>
	<div id="main">
		<div class="page-content">
	        <section class="row">
	            <div class="col-12">
	                <div class="row">
	                    <div class="col-6">
	                        <div class="card" align="center">
	                            <div class="card-body">
	                                <div align="center">
										<div style="width:350px; height:370px;">
										   	<h4>자유 게시판</h4>
											<canvas id="myChart1"></canvas>
										</div>
						            </div>
						        </div>
						    </div>
						</div>
		                <div class="col-6">
	                        <div class="card" align="center">
	                            <div class="card-body">
	                                <div align="center">
										<div style="width:350px; height:370px;">
										   	<h4>정보공유 게시판</h4>
											<canvas id="myChart2"></canvas>
										</div>
						            </div>
						        </div>
						    </div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
        
<script type="text/javascript">
    var context = document.getElementById('myChart1').getContext('2d');
    
    var myChart = new Chart(context, {
        type: 'pie',//차트의 형태 (line ,bar, pie)선택
        data: { // 차트에 들어갈 데이터
            labels: [
                //x 축
                '비회원','회원'
            ],
            datasets: [
                { //데이터
                    label: 'test1', //차트 제목
                    fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                    data: [
                        ${guestFreeCount}, ${memberFreeCount} //x축 label에 대응되는 데이터 값
                    ],
                    backgroundColor: [
                        //색상
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)'
                        
                    ],
                    borderColor: [
                        //경계선 색상
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)'
                        
                    ],
                    borderWidth: 1 //경계선 굵기
                }/* ,
                {
                    label: 'test2',
                    fill: false,
                    data: [
                        8, 34, 12, 24
                    ],
                    backgroundColor: 'rgb(157, 109, 12)',
                    borderColor: 'rgb(157, 109, 12)'
                } */
            ]
        },
        options: {
            scales: {
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true
                        }
                    }
                ]
            }
        }
    });
</script>
<script type="text/javascript">
    var context = document.getElementById('myChart2').getContext('2d');
    
    var myChart = new Chart(context, {
        type: 'pie',//차트의 형태 (line ,bar, pie)선택
        data: { // 차트에 들어갈 데이터
            labels: [
                //x 축
                '비회원','회원'
            ],
            datasets: [
                { //데이터
                    label: 'test2', //차트 제목
                    fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                    data: [
                    	${guestInfoCount}, ${memberInfoCount} //x축 label에 대응되는 데이터 값
                    ],
                    backgroundColor: [
                        //색상
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)'
                       
                    ],
                    borderColor: [
                        //경계선 색상
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)'
                        
                    ],
                    borderWidth: 1 //경계선 굵기
                }/* ,
                {
                    label: 'test2',
                    fill: false,
                    data: [
                        8, 34, 12, 24
                    ],
                    backgroundColor: 'rgb(157, 109, 12)',
                    borderColor: 'rgb(157, 109, 12)'
                } */
            ]
        },
        options: {
            scales: {
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true
                        }
                    }
                ]
            }
        }
    });
</script>
</body>
</html>