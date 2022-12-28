<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="resources/css/layoutleft.css">
<body>
<link rel="stylesheet" type="text/css" href="resources/css/layoutleft.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://kit.fontawesome.com/0b3bdf6c61.js" crossorigin="anonymous"></script>
<script>
	$(function() {
		var Accordion = function(el, multiple) {
			this.el = el || {};
			// more then one submenu open?
			this.multiple = multiple || false;

			var dropdownlink = this.el.find('.dropdownlink');
			dropdownlink.on('click', {
				el : this.el,
				multiple : this.multiple
			}, this.dropdown);
		};

		Accordion.prototype.dropdown = function(e) {
			var $el = e.data.el, $this = $(this),
			//this is the ul.submenuItems
			$next = $this.next();

			$next.slideToggle();
			$this.parent().toggleClass('open');

			if (!e.data.multiple) {
				//show only one menu at the same time
				$el.find('.submenuItems').not($next).slideUp().parent()
						.removeClass('open');
			}
		}

		var accordion = new Accordion($('.accordion-menu'), false);
	})
</script>
	<div class="leftbox">
		<ul class="accordion-menu">
			<li>
				<div class="dropdownlink">
					센터 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="<c:url value='/centerList'/>">센터 조회</a></li>
					<li><a href="<c:url value='/centerPhoto'/>">센터 사진 관리</a></li>
					<li><a href="#">센터 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					담당자 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="#">담당자 조회</a></li>
					<li><a href="#">담당자 맵핑</a></li>
					<li><a href="#">담당자 정보 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					점포점수 <i class="fa fa-chevron-down" aria-hidden="true"></i>
				</div>
				<ul class="submenuItems">
					<li><a href="#">센터 점수 조회</a></li>
					<li><a href="#">항목 코드 관리</a></li>
					<li><a href="#">센터 점수 일괄 등록</a></li>
				</ul>
			</li>
			<li>
				<div class="dropdownlink">
					<a href="<c:url value='/statistics'/>">통계 </a>
				</div>
			</li>
		</ul>
	</div>
