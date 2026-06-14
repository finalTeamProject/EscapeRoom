<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/common.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="ne-main-content ne-body-offset">
    <div class="ne-container d-flex justify-content-center align-items-start py-5">
        <div class="card shadow-sm" style="width: 480px;">
            <div class="card-body p-4">
                <h5 class="card-title fw-bold mb-1">회원탈퇴</h5>
                <p class="text-muted small mb-4">탈퇴하시면 개인정보가 삭제되며 복구할 수 없습니다.</p>

                <div id="errorMsg" class="alert alert-danger d-none" role="alert"></div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">비밀번호 확인</label>
                    <input type="password" id="password" class="form-control" placeholder="현재 비밀번호를 입력하세요">
                </div>

                <div class="d-flex gap-2 mt-4">
					<button type="button" class="btn btn-danger flex-fill"
					        onclick="confirmWithdraw()">탈퇴하기</button>
                    <button type="button" class="btn btn-outline-secondary flex-fill"
                            onclick="history.back()">취소</button>
                   
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<script>
function confirmWithdraw() {
    const password = document.getElementById('password').value.trim();
    if (!password) {
        showError('비밀번호를 입력해주세요.');
        return;
    }

    if (!confirm('정말로 탈퇴하시겠습니까?\n탈퇴 후에는 복구가 불가능합니다.')) return;

    fetch('${pageContext.request.contextPath}/user/withdraw', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'password=' + encodeURIComponent(password)
    })
    .then(res => res.json())
    .then(data => {
        if (data.status === 'success') {
            alert('탈퇴가 완료되었습니다. 이용해 주셔서 감사합니다.');
            location.href = '${pageContext.request.contextPath}/user/login';
        } else {
            showError(data.message);
        }
    })
    .catch(() => showError('오류가 발생했습니다. 다시 시도해주세요.'));
}

function showError(msg) {
    const el = document.getElementById('errorMsg');
    el.textContent = msg;
    el.classList.remove('d-none');
}
</script>
</body>
</html>
