import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import './UserRegistration.css'
import axios from 'axios';
 
const UpdateProfile = () => {
  const navigate = useNavigate();
  const location=useLocation();
  const tokens=localStorage.getItem('token');
    const [data, setData] = useState({
        userName: location.state.userName,
        userEmail: location.state.userEmail,
        mobileNumber: location.state.mobileNumber,
        userAddress: location.state.userAddress
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
        const response= await axios.put(`http://localhost:8082/api/auth/updateUser/${location.state.userId}`, data,{
          method: "PUT",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
  }
        });
            alert("updated successful")
            navigate('/')
        } catch (err) {
            alert(err.response?.data?.message || "updation failed. Please try again.")
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
                    <h2>Update your profile</h2>
                </div>

                <form className="register-form" onSubmit={submitHandler}>
                    <div className="form-group">
                        <label>Name</label>
                        <input type='text' name="userName" placeholder='Enter your name' value={data.userName} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type='email'  name="userEmail" placeholder='Enter your email' value={data.userEmail} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Mobile number</label>
                        <input type='tel' name="mobileNumber"  placeholder='Enter your mobile number' value={data.mobileNumber} onChange={changeHandler} />
                    </div>

                    <div className="form-group">
                        <label>Address</label>
                        <input type='text' name="userAddress" placeholder='Enter your address' value={data.userAddress} onChange={changeHandler} />
                    </div>

                    <button type="submit" className="submit-btn">Update</button>

                </form>
            </div>
        </div>
    )
}


export default UpdateProfile
