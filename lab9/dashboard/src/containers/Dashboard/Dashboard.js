import React from 'react'
import '../Headers/Header.css'
import Header from "../Headers/Header"
import PageRoutes from "./PageRoutes"

const Dashboard = () => {
    return (
        <React.Fragment>   
            <div className='header'>
                <Header />
            </div>
            <div className="Product">
                <PageRoutes />
            </div>
        </React.Fragment>
    )
}

export default Dashboard;