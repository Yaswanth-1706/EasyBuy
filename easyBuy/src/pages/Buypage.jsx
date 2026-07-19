import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './Buypage.css';
import Nav from './Nav';
import axios from 'axios';

const Buypage = () => {
    const location = useLocation();
    const navigate=useNavigate()
    const [paymentMethod, setPaymentMethod] = useState("");
    const user=JSON.parse(localStorage.getItem('user'))
   const tokens=localStorage.getItem('token')
    const placeOrder=async()=>{
         const id = "ORD" + Date.now();
         const data={
        "userId":user.userId,
        "productId":location.state.productId,
        "productOrderId":id
    }
        try{
        const response=await axios.post("https://easybuy-rztb.onrender.com/api/auth/order/createOrder",data,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
  }
        })
        navigate("/order",{state:data})
        }
        catch(err)
        {

            console.log(err)
            navigate('/')
        }
    }

    return (
        <div>
            <Nav/>
        <div className="buy-page">
    <div className="buy-card">
       
        <h2 className="buy-heading">Order Summary</h2>
        <center>
        <img className="order-image" src={location.state.productImage}/>
        </center>
        <div className="buy-row">
            <span className="buy-label">Product</span>
            <span className="buy-value">{location.state.productName}</span>
        </div>

        <div className="buy-row">
            <span className="buy-label">Price</span>
            <span className="buy-value">₹{location.state.price}</span>
        </div>

        <div className="buy-row">
            <span className="buy-label">Delivery</span>
            <span className="buy-value">FREE</span>
        </div>

        <div className="buy-total">
            <span>Total</span>
            <span>₹{location.state.price}</span>
        </div>

        <div className="payment-box">
            <h4>Choose Payment Method</h4>

            <select
                className="payment-select"
                value={paymentMethod}
                onChange={(e) => setPaymentMethod(e.target.value)}
            >
                <option value="">Select Payment Method</option>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="UPI">UPI</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
            </select>
        </div>

        <button className="place-btn" onClick={()=>placeOrder()}>
            Place Order
        </button>

    </div>
</div>
</div>
    );
};

export default Buypage;