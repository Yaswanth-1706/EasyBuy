import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import './EachProductDetails.css'
import Nav from "./Nav";

const EachProductDetails = () => {
    const location = useLocation();
    const data = location.state;
    const user=JSON.parse(localStorage.getItem('user'))
    console.log("user",user.userId);
    console.log(data);
    const navigate = useNavigate();
    const [relatedItems, setRelatedItems] = useState([])
  const tokens=localStorage.getItem('token');
    useEffect(() => {
        const fetch = async () => {
            try{
            const response = await axios.get(`https://easybuy-production.up.railway.app/api/auth/products/getProductByCategory/${data.categoryName}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
  }
        });
            setRelatedItems(response.data);
    }
    catch(err){
        navigate('/')
    }
        }
        fetch();
    }, [])
    useEffect(() => {
    window.scrollTo(0, 0);
}, [data]);
const addToCart=async(id)=>{
const cartData={
    "userId":user.userId,
    "productId":id
}
console.log(cartData)
try{
 const response=await axios.post("https://easybuy-production.up.railway.app/api/auth/cart/addcart",cartData,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
  }
        })
 alert("product added to cart sucssfully")
}
catch(err){
    alert("adding to cart failed")
    navigate('/')
}
}

    return (
        <div className="main-page">
            <Nav/>
        <div className="details-page">
            <div className="details-main">
                <div className="details-image-wrap">
                    <img className="details-image" src={data.productImage} />
                </div>
                <div className="details-info">
                    <h2 className="details-name">{data.productName}</h2>
                    <h1 className="details-price">₹{data.price}</h1>
                    <h2 className="details-quantity">Quantity: {data.quantity}</h2>
                    <h3 className="details-desc-heading">Description</h3>
                    <h5 className="details-desc">{data.productDescription}</h5>
                    <h1 className="details-stock">{data.stock}</h1>
                    <button onClick={()=>addToCart(data.productId)} className="cart-btn">Add to Cart</button>
                    
                </div>
            </div>

            <div className="related-section">
                <h3 className="related-heading">You may also like</h3>
                <div className="related-grid">
                    {
                        relatedItems.map((item, key) => {
                            return (
                                <div className="related-card" key={key}>
                                    <div className="related-image-wrap">
                                        <img
                                            className="related-image"
                                            src={item.productImage}
                                            onClick={() => { navigate('/eachProduct', { state: item }) }}
                                        />
                                    </div>
                                    <h6 className="related-name">{item.productName}</h6>
                                </div>
                            )
                        })
                    }
                </div>
            </div>
        </div>
        </div>
    );
};

export default EachProductDetails;