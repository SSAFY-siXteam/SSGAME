export function checkForm(info) {
  // 회원가입 버튼 로직 : 아이디 확인 여부, 비밀번호 두개 일치, 이메일 유무
  if (info.idInput === "") {
    alert("아이디를 입력해주세요.");
    return false;
  } else if (info.idInput.length < 4 || info.idInput.length > 16) {
    alert("아이디는 최소 4자리 이상, 16자리 이하입니다.");
    return false;
  } else if (info.passwordInput === "" || info.passwordCheckInput === "") {
    alert("비밀번호를 입력해주세요.");
    return false;
  } else if (info.passwordInput.length < 8 || info.idInput.length > 16) {
    alert("비밀번호는 최소 8자리 이상, 16자리 이하입니다.");
    return false;
  } else if (
    !info.passwordInput.match(
      /([0-9]+[A-Z]+|[A-Z]+[0-9])+[0-9a-zA-Z!@#$%]*|([0-9]+[a-z]+|[a-z]+[0-9])+[0-9a-zA-Z!@#$%]*|([0-9]+[!@#$%]+|[!@#$%]+[0-9])+[0-9a-zA-Z!@#$%]*|([A-Z]+[a-z]+|[a-z]+[A-Z])+[0-9a-zA-Z!@#$%]*|([A-Z]+[!@#$%]+|[!@#$%]+[A-Z])+[0-9a-zA-Z!@#$%]*|([a-z]+[!@#$%]+|[!@#$%]+[a-z])+[0-9a-zA-Z!@#$%]*/
    )
  ) {
    alert("비밀번호는 대문자, 소문자, 숫자, 특수문자 중 2개를 포함 해 주세요");
    return false;
  } else if (info.passwordInput !== info.passwordCheckInput) {
    alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
    return false;
  } else if (info.emailInput === "") {
    alert("이메일을 입력해주세요.");
    return false;
  } else if (info.idCheck === false) {
    alert("아이디 확인을 해주세요.");
    return false;
  } else {
    return true;
  }
}
