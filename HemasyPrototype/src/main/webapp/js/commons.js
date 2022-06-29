function openModal() {
    let gray_out = document.getElementById("fadeLayer");
    gray_out.style.visibility = "visible";
    setTimeout(addClass, 200);
}

function closeModal() {
    let modal = document.getElementById('modal');
    let gray_out = document.getElementById("fadeLayer");
    modal.classList.remove('is-show');
    gray_out.style.visibility ="hidden";
}

function addClass() {
    let modal = document.getElementById('modal');
    modal.classList.add('is-show');
}

document.querySelector('.menu-btn').addEventListener('click', function(){
   document.querySelector('.menu').classList.toggle('is-active');
});

'use strict';
{
  const open = document.getElementById('open');
  const close = document.getElementById('close');
  const modal = document.getElementById('modal');
  const mask = document.getElementById('mask');

  open.addEventListener('click', function () {
    modal.classList.remove('hidden');
    mask.classList.remove('hidden');
  });
  close.addEventListener('click', function () {
    modal.classList.add('hidden');
    mask.classList.add('hidden');
  });
  mask.addEventListener('click', function () {
    modal.classList.add('hidden');
    mask.classList.add('hidden');
  });
}


let achievementList;
let achievementName;
function getAchievementName() {
	fetch('/getAchivementName?id=' + Array.from(document.getElementsByName("achievementId")).find(e => e.checked).value)
	.then(res => res.json().then(data => {
				achievementList = data
				achievementName = achievementList.achievementName
				let tatsuki = document.getElementById("tatsuki");
				tatsuki.value= (achievementName);
			},

		)
	)
};

