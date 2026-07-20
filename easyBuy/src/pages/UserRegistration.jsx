import React, { useState } from 'react'
import axios from "axios"
import { useNavigate } from 'react-router-dom';
import './UserRegistration.css';

const UserRegistration = () => {
    const navigate = useNavigate();
    const [data, setData] = useState({
        userName: "",
        userEmail: "",
        password: "",
        mobileNumber: "",
        userAddress: ""
    });

    const changeHandler = (e) => {
        setData({
            ...data,
            [e.target.name]: e.target.value
        })
    }

    const submitHandler = async (e) => {
        e.preventDefault()
        try {
            await axios.post("https://easybuy-rztb.onrender.com/api/auth/register", data);
            alert("Registration successful")
            navigate('/')
        } catch (err) {
            alert(err.response?.data?.message || "Registration failed. Please try again.")
        }
    }

    return (
        <div className="register-page">
            <div className="form-panel">
                <div className="brand-mark">
                    <h1>EasyBuy</h1>
                    <p>Everything you need, one place</p>
                </div>

                <div className="form-heading">
                    <h2>Create your account</h2>
                    <p>Sign up to start shopping</p>
                </div>

                <form className="register-form" onSubmit={submitHandler}>
                    <div className="form-group">
                        <label>Name</label>
                        <input type='text' name="userName" placeholder='Enter your name' value={data.userName} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type='email' name="userEmail" placeholder='Enter your email' value={data.userEmail} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type='password' name="password" placeholder='Enter your password' value={data.password} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Mobile number</label>
                        <input type='tel' name="mobileNumber" placeholder='Enter your mobile number' value={data.mobileNumber} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Address</label>
                        <input type='text' name="userAddress" placeholder='Enter your address' value={data.userAddress} onChange={changeHandler} />
                    </div>

                    <button type="submit" className="submit-btn">Register</button>

                    <p className="login-redirect">
                        Already have an account?{' '}
                        <span onClick={() => navigate('/')}>Login here</span>
                    </p>
                </form>
            </div>
        </div>
    )
}

export default UserRegistration