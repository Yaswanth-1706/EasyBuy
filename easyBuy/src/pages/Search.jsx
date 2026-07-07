import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom';
import './Search.css'
import SearchNav from './SearchNav';
const Search = () => {
    const [search,setSearch]=useState("");
    const [data,setData]=useState([]);
    const navigate=useNavigate();
    const tokens=localStorage.getItem('token');
    const handleSearch=(e)=>{
        setSearch(e.target.value);
    }
    useEffect(()=>{
      try{
        const fetch=async()=>{
     if(search!=="")
     {
     const response=await axios.get(`http://localhost:8082/api/auth/products/getProductByProductName/${search}`,{
          method: "GET",
          headers: {
    "Authorization": `Bearer ${tokens}`,
  }
        })
     setData(response.data)
     }
     else
        setData([]);
        }
        fetch();
      }
      catch(err){
        navigate('/')
      }
    },[search])
 return (
  <div className='total'>
   <SearchNav search={search} setSearch={setSearch} />
<center>
<div className="search-result-page">
  {data.length > 0 ? (
    <div className="search-results">
      {data.map((item, key) => (
        <h3
          className="search-result-item"
          key={key}
          onClick={() => navigate('/eachProduct', { state: item })}
        >
          {item.productName}
        </h3>
      ))}
    </div>
  ) : (
    
    search !== "" && <p className="search-empty">No products found</p>
      
  )}
</div>
  </center>
 
  </div>
)
}

export default Search
