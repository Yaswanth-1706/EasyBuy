import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./UserLogin.css";

const UserLogin = () => {
  const navigate = useNavigate();

  const [loginData, setLoginData] = useState({
    userEmail: "",
    password: "",
  });

  const changeHandler = (e) => {
    setLoginData({
      ...loginData,
      [e.target.name]: e.target.value,
    });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      const token = await axios.post(
        "https://easybuy-user.onrender.com/api/auth/login",
        loginData
      );

      localStorage.setItem("token", token.data.token);

      const user = await axios.get(
        `https://easybuy-user.onrender.com/api/auth/getUsersByEmail/${token.data.udto.userEmail}`,
        {
          headers: {
            Authorization: `Bearer ${token.data.token}`,
          },
        }
      );

      localStorage.setItem("user", JSON.stringify(user.data));

      alert("Login Successful");
      navigate("/products");
    } catch (err) {
      alert("Invalid Credentials");
    }
  };

  return (
    <div className="login-page">
      <div className="login-panel">

        <div className="brand-mark">
          <h1>EasyBuy</h1>
          <p>Everything you need, one place</p>
        </div>

        <div className="form-heading">
          <h2>Welcome Back</h2>
          <p>Login to continue shopping</p>
        </div>

        <form className="login-form" onSubmit={submitHandler}>

          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              name="userEmail"
              placeholder="Enter your email"
              onChange={changeHandler}
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              name="password"
              placeholder="Enter your password"
              onChange={changeHandler}
            />
          </div>

          <button className="submit-btn">Login</button>

          <div className="login-redirect">
            Don't have an account?{" "}
            <span onClick={() => navigate("/register")}>
              Register here
            </span>
          </div>

        </form>

      </div>
    </div>
  );
};

export default UserLogin;