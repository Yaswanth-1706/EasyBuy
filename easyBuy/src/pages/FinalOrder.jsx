import React from 'react'
import './FinalOrder.css';
import { useLocation, useNavigate } from 'react-router-dom';
import Nav from './Nav';

const FinalOrder = () => {
  const navigate=useNavigate()
  const location=useLocation()
    return (
        <div>
            <Nav/>
        <div className="order-pages">

            <div className="order-cards">

                <div className="success-icon">✓</div>

                <h1 className="order-headings">
                    Order Placed Successfully
                </h1>

                <div className="order-rows">
                    <span className="order-label">Order ID</span>
                    <span className="order-value">{location.state.productOrderId}</span>
                </div>

                <div className="order-rows">
                    <span className="order-label">Payment Status</span>
                    <span className="payment-success">SUCCESS</span>
                </div>

                <div className="order-rows">
                    <span className="order-labels">Estimated Delivery</span>
                    <span className="order-values">
                        3 - 5 Business Days
                    </span>
                </div>

                <p className="order-messages">
                    We'll send you an email with your order details and
                    tracking updates.
                </p>

                <div className="order-buttons">
                    <button className="continue-btn" onClick={()=>navigate("/products")}>
                        Continue Shopping
                    </button>

                    <button className="orders-btns" onClick={()=>navigate("/orders")}>
                        View Orders
                    </button>
                </div>

            </div>

        </div>
        </div>
    )
}

export default FinalOrder;