// 修改 form 的 action
function updateExam(index) {
	document.getElementById('exam').action = '/springmvcgit/mvc/case03/exam/' + index;
	document.getElementById('exam').submit();
}
function deleteExam(index) {
	document.getElementById('_method').value = 'DELETE';
	updateExam(index);
}

function updateExamNote(index, examNote) {
	var newExamNote = prompt('請輸入考試備註(examNote)資料', examNote); 	// 跳出對話框
	document.getElementById('_method').value = 'PUT';				// 更改為PUT
	document.getElementById('examNote').value = newExamNote;		// 更改備註的資料					// 更改action
	document.getElementById('exam').action = '/springmvcgit/mvc/case03/exam/' + index + '/exam_note';
	document.getElementById('exam').submit();						// 送出

}