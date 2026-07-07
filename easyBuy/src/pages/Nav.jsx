import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import './Nav.css'
import profile from "../assets/profile.jpg"
import { HiOutlineShoppingBag } from "react-icons/hi2";
import { FiSearch } from 'react-icons/fi';

const Nav = () => {
  const navigate = useNavigate();
  const [showDropdown,setShowDropdown]=useState(false);
  const user = JSON.parse(localStorage.getItem('user'))||null;
  const userName=user?user.userName:user;
  const logout=()=>{
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    navigate('/')
  }
  return (
    <nav className="Nav">
      
      <div className="Nav-left">
        <h1 className="logo">
    <HiOutlineShoppingBag className="logo-icon" />
    EasyBuy
</h1>
        <button className="Nav-back" onClick={() => navigate(-1)}>Back</button>
      </div>

     <center>
     <div className="search-page">
    <input type="text" placeholder="Enter product names"  onClick={()=>navigate('/search')}/>
  </div>     
  </center>   
      <div className="Nav-right">
              <h3>{userName}</h3>
              {/* <h3 className='space'>"""""""""""</h3> */}
              <img className="profile"  onClick={()=>setShowDropdown(!showDropdown)} src={profile} alt="User Profile" />
            </div>
           {showDropdown && (
                <div className="profile-dropdown">
                   <h3 className="profile-name">{userName}</h3>
                   <h4 onClick={()=>navigate("/profile",{state:user})}>Profile</h4>
                   <h4 onClick={()=>navigate("/cart")}>Cart</h4>
                   <h4 onClick={()=>navigate("/orders")}>Orders</h4>
                  <button onClick={()=>logout()} >Logout</button>
                </div>
        )}
    </nav>
  )
}

export default Nav;