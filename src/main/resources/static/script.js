function login() {

    const regNo = document.getElementById("regNo").value;
    const password = document.getElementById("password").value;
    const result = document.getElementById("result");

    result.innerText = "Checking...";
    result.style.color = "#7c3aed";

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            regNo: regNo,
            password: password
        })
    })
    .then(res => res.json())
    .then(data => {

        if (data.status === "SUCCESS") {
            result.style.color = "green";
            result.innerText = "✅ Login Successful";
        }
        else if (data.status === "BLOCKED") {
            result.style.color = "orange";
            result.innerText = "🚫 Too many attempts. Blocked!";
        }
        else {
            result.style.color = "red";
            result.innerText = "❌ Invalid credentials";
        }
    })
    .catch(() => {
        result.style.color = "red";
        result.innerText = "Server error";
    });
}
