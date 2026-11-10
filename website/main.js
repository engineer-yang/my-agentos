'use strict';

document.querySelectorAll('[data-copy]').forEach(function (button) {
  button.hidden = false;
  button.addEventListener('click', async function () {
    var code = document.getElementById(button.dataset.copy);
    var status = document.getElementById('copy-status');
    try {
      await navigator.clipboard.writeText(code.textContent);
      status.textContent = '已复制命令：' + code.textContent;
      button.textContent = '已复制';
    } catch (error) {
      var range = document.createRange();
      range.selectNodeContents(code);
      var selection = window.getSelection();
      selection.removeAllRanges();
      selection.addRange(range);
      status.textContent = '浏览器无法自动复制，命令已选中，请按 Ctrl+C 或 Command+C 复制。';
      button.textContent = '已选中，请手动复制';
    }
    window.setTimeout(function () { button.textContent = '复制'; }, 2500);
  });
});
