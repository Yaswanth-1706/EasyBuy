import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import './Products.css'
import Nav from './Nav'

const Products = () => {
  const [loading,setLoading]=useState(false)
  const navigate = useNavigate();
  const [data, setData] = useState([])
  const [categorys,setCategorys]=useState([]);
  const[filter,setFilter]=useState("getProducts");
  const [selectedCategory, setSelectedCategory] = useState(null)
  const tokens=localStorage.getItem('token');
  useEffect(() => {
    
      
    const fetchCat = async () => {
      try{
  const response = await axios.get(
    "https://easybuy-production.up.railway.app/api/auth/category/getCategory",{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`
          }
        }
  );
  setCategorys(response.data);
}
catch(err){
  navigate("/")
}
}


 fetchCat();

},[])
  useEffect(() => {
    
    const fetchProducts = async () => {
      try{
        setLoading(true)
      const response = await axios.get(
        `https://easybuy-production.up.railway.app/api/auth/products/${filter}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
    "Content-Type": "application/json"
  }
        }
      )
      setData(response.data);

    }
    catch(err){
      navigate('/')
     }
    finally{
      setLoading(false)
     }
    }
  
    fetchProducts();
  }, [filter]);
   const handleCategoryClick = (categoryName) => {
    setSelectedCategory(categoryName);
    setFilter(`getProductByCategory/${categoryName}`);
  };
 return (
  
  <div className="main-products">
    <Nav />       
    {loading ? (
      <div className="loading-container">
        <div className="loader"></div>
        <h2>Loading...</h2>
      </div>
    ) : (
      <div> 
     <div className="search-bkg">
    <input className='search-page' type="text" placeholder="Enter product names"  onClick={()=>navigate('/search')}/>
     </div>  
      <div className="products-page">
        {/* Categories */}
        <div className="sideBar">
          <table>
            <tbody>
              {categorys.map((item, key) => (
                <tr
                  key={key}
                  className={
                    selectedCategory === item.category ? "active" : ""
                  }
                  onClick={() => handleCategoryClick(item.category)}
                >
                  <th>{item.category}</th>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Products */}
        <div className="products-grid">
          {data.map((item, key) => (
            <div className="product-card" key={key}>
              <div
                className="product-image-wrap"
                onClick={() =>
                  navigate("/eachProduct", { state: item })
                }
              >
                <img
                  className="product-image"
                  src={item.productImage}
                  alt={item.productName}
                />
              </div>

              <h6 className="product-name">
                {item.productName}
              </h6>

              <p className="product-price">
                ₹{item.price}
              </p>
            </div>
          ))}
        </div>

      </div>
      </div>
    )}
  </div>
);
}

export default Products