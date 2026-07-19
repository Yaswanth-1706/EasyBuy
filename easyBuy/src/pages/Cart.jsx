import React, { useEffect, useState } from 'react'
import axios from 'axios'
import './Cart.css'
import Nav from './Nav';
import { useNavigate } from 'react-router-dom';
const Cart = () => {
    const navigate=useNavigate();
    const [data,setData]=useState([]);
    const [product,setProduct]=useState([])
    const tokens=localStorage.getItem('token')
    const user=JSON.parse(localStorage.getItem('user'))
     const fetchdata=async() => {
        try{
            const response=await axios.get(`https://easybuy-rztb.onrender.com/api/auth/cart/getCartById/${user.userId}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
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
            const arr = await Promise.all(
      data.map(async (item,key) => {
        const response = await axios.get(
            `https://easybuy-rztb.onrender.com/api/auth/products/getProduct/${item.productId}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
  }
        }
        );
        return response.data;
    })
    
      );    
     setProduct(arr)
    }
   catch(err){
    navigate('/')
   }
         }

    useEffect(()=>{
        fetchdata()
     } ,[]);

     useEffect(()=>{
         fetch()       
     } ,[data]);
     const removeItem= async(id)=>{
        let deleteId=-1;
        for(const item of data){
            if(id===item.productId)
            {
                   deleteId=item.cartId;
            }
        }
        try{
        const response=await axios.delete(`https://easybuy-rztb.onrender.com/api/auth/cart/deletecart/${deleteId}`,{
          method: "DELETE",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
  }
        })
        alert("Item removed")
        fetchdata()
        }
        catch(err){
            alert(err)
            navigate('/')
        }
     }
  return (
    <div className="main-cart">
         <Nav/>
    <div className="cart-page">
        <div className="cart-container">

            {
                product.map((item, key) => {

                    return (

                        <div className="cart-item" key={key}>

                            <div className="cart-image-box">
                                <img className="cart-image" src={item.productImage} alt={item.productName} />
                            </div>
                            <div className="cart-info">
                                <h2 className="cart-title">{item.productName}</h2>
                                <h1 className="cart-price">₹{item.price}</h1>
                                 <h3 className="cart-quantity">Quantity : {item.quantity}</h3>
                                 <h3 className="cart-description-title">Description</h3>
                                 <p className="cart-description">{item.productDescription}</p>
                                  <h3 className="cart-stock"> {item.stock} </h3>
                               <div className="cart-btn-group">
                                  <button onClick={()=>removeItem(item.productId)} className="cart-remove-btn">Remove</button>
                                  <button className="cart-buy-btn" onClick={()=>navigate('/buypage',{state:item})}>Buy Now</button>
                                  </div>
                                  </div>
                                  </div>
                                  
                                  )
                                })
            }

        </div>

    </div>
    </div>
)
}

export default Cart
