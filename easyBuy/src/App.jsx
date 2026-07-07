import React from 'react'
import UserRegistration from './pages/UserRegistration'
import { Route, Routes } from 'react-router-dom'
import UserLogin from './pages/UserLogin'
import Products from './pages/Products'
import EachProductDetails from './pages/EachProductDetails'
import Search from './pages/Search'
import Nav from './pages/Nav'
import Profile from './pages/Profile'
import UpdateProfile from './pages/UpdateProfile'
import Cart from './pages/Cart'
import Buypage from './pages/Buypage'
import FinalOrder from './pages/FinalOrder'
import Orders from './pages/Orders'

const App = () => {
  return (
    <div>
      
      <Routes>
        <Route path='/' element={<UserLogin/>}/>
        <Route path='/register' element={<UserRegistration/>}/>
        <Route path='/products' element={<Products/>}/>
        <Route path='/eachProduct' element={<EachProductDetails/>}/>
        <Route path='/search' element={<Search/>}/>
        <Route path='/profile' element={<Profile/>}/>
        <Route path='/updateProfile' element={<UpdateProfile/>}/>
        <Route path="/cart" element={<Cart/>}/>
        <Route path="/buypage"  element={<Buypage/>}/>
        <Route path="/order" element={<FinalOrder/>}/>
        <Route path="/orders" element={<Orders/>}/>
      </Routes>
      
    </div>
  )
}

export default App
