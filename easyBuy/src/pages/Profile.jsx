import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import profile from '../assets/profile.jpg';
import './Profile.css';
import Nav from './Nav';

const Profile = () => {

    const location = useLocation();
    const navigate = useNavigate();

    return (
        <div>
            <Nav/>
        <div className="profile-container">

            <div className="profile-card">

                <img
                    src={profile}
                    alt="Profile"
                    className="profile-image"
                />

                <h2 className="profile-title">My Profile</h2>

                <div className="profile-info">
                    <p><span>User Name :</span> {location.state.userName}</p>

                    <p><span>Email :</span> {location.state.userEmail}</p>

                    <p><span>Mobile Number :</span> {location.state.mobileNumber}</p>

                    <p><span>Address :</span> {location.state.userAddress}</p>
                </div>

                <button
                    className="edit-btn"
                    onClick={() =>
                        navigate('/updateProfile', { state: location.state })
                    }
                >
                    Edit Profile
                </button>

            </div>

        </div>
        </div>
    );
};

export default Profile;