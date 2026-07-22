import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import Nav from './Nav'
import './Orders.css'

const Orders = () => {
    const location =useLocation()
    const [data,setData]=useState([])
    const user=JSON.parse(localStorage.getItem('user'))
    const [product,setProduct]=useState([]);
    const tokens=localStorage.getItem('token')
    const navigate=useNavigate()
    const fetchData=async()=>{
        try{
     const response=await axios.get(`https://easybuy-user.onrender.com/api/auth/order/getOrderById/${user.userId}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
  }
        })
     setData(response.data)
    }
    catch(err){
        navigate('/')
    }
    }
    const fetch=async()=>{
        try{
        const arr=await Promise.all(
            data.map(async(item)=>{
                   
                const response=await axios.get(`https://easybuy-user.onrender.com/api/auth/products/getProduct/${item.productId}`,{
                   method: "GET",
                   headers: {
                      "Authorization": `Bearer ${tokens}`,
                            }
                        })
                     return (
                        {
                        "data":response.data,
                        "order":item.productOrderId,
                        "OID":item.orderId
                        }
                    );
            })
       
        
        )
        setProduct(arr)
    }
    catch(err){
        navigate('/')
    }
    }
    useEffect(()=>{
        fetchData()
    },[])
    useEffect(()=>{
       fetch()
    },[data])
  const remove=async(id)=>{
    try{
    const removedata=await axios.delete(`https://easybuy-user.onrender.com/api/auth/order/deleteOrder/${id}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
  }
        })
    
    fetchData()
    }
    catch(err){
        navigate('/')
    }

  }
  return (
        <>
            <Nav/>

            <div className="orders-page">

                <h1 className="orders-heading">My Orders </h1>

                {
                    data.length === 0 ?

                        <h2 className="empty-order"> No Orders Found </h2> :

                        product.map((item) => (
                            <div className="order-card">
                              <div className="order-image-section">
                                <img src={item.data.productImage} alt="" className="order-image" />

                                </div>

                                <div className="order-details">

                                    <h2 className="order-product-name">
                                        {item.data.productName}
                                    </h2>

                                    <p className="order-id">
                                        <b>Order ID:</b> {item.order}
                                    </p>

                                    <p className="order-price">
                                        ₹{item.data.price}
                                    </p>

                                    <p>
                                        <b>Quantity :</b> 1
                                    </p>

                                    <p>
                                        <b>Payment :</b> {item.data.paymentMethod}
                                    </p>

                                    <p className="payment-success">
                                        Payment Successful
                                    </p>

                                    <p className="delivery-date">
                                        Estimated Delivery :
                                        <b> 3-5 Business Days</b>
                                    </p>

                                </div>

                                <div className="order-status">

                                    <span className="status processing">
                                        ● Processing
                                    </span>

                                    <button className="details-btn" onClick={()=>navigate("/eachProduct",{state:item.data})}>
                                        View Details
                                    </button>
                                    <button className="remove-btn" onClick={() => remove(item.OID)}> Remove Order</button>

                                </div>

                            </div>

                        ))
                }

            </div>
        </>
    );
}

export default Orders
