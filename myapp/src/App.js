import React from "react";
import "./App.css";

// Lab imports
import GroceryList from "./REACTFILES/GroceryList";
import Car from "./REACTFILES/Car";
import Phone from "./REACTFILES/Phone";
import SweetsList from "./REACTFILES/SweetsList";
import Electronics from "./REACTFILES/Electronics";
import CanteenMenu from "./REACTFILES/CanteenMenu";
import JuiceList from "./REACTFILES/JuiceList";
import Restaurant from "./REACTFILES/Restaurant";
import TempleList from "./REACTFILES/TempleList";
import TailorShop from "./REACTFILES/TailorShop";
import Fruits from "./REACTFILES/Fruits";
import TelevisionManager from "./REACTFILES/Television";
import MarriageForm from "./REACTFILES/MarriageForm";
import BakingItemsForm from "./REACTFILES/BakingItemsForm";
import AccessoriesForm from "./REACTFILES/AccessoriesForm";
import FlightBooking from "./REACTFILES/FlightBooking";
import MovieForm from "./REACTFILES/MovieForm";
import ElectronicProductApp from "./REACTFILES/ElectronicProductApp"; 
import FurnitureStore from "./REACTFILES/FurnitureStore";
import RestaurantForm from "./REACTFILES/RestaurantForm";
import ParentChildSibling from "./REACTFILES/ParentChildSibling";
import ChessTournamentForm from "./REACTFILES/Chess Tournament Form";
import HockeyTournamentForm from "./REACTFILES/Hockey Tournament Form";
import TailoringInventory from './REACTFILES/TailoringInventory';
import FootballPlayerManagement from './REACTFILES/FootballPlayerManagement';


function App() {
  const groceryItems = ["Rice", "Wheat", "Sugar", "Milk", "Oil"];

  return (
    <div className="App">
      {/* Uncomment the lab you want to test */}

       <GroceryList items={groceryItems} /> 
      <Car brand="Toyota" model="Fortuner" color="Black" year="2022" /> 
       <Phone /> 
       <SweetsList /> 
      <Electronics /> 
       <CanteenMenu /> 
       <JuiceList /> 
       <Restaurant /> 
       <TempleList /> 
       <TailorShop />
       <Fruits />
       <TelevisionManager />
       <MarriageForm />
       <BakingItemsForm />
       <AccessoriesForm />
       <FlightBooking />
       <MovieForm />
       <ElectronicProductApp />
       <FurnitureStore />
       <RestaurantForm />
       <ParentChildSibling />
       
      
       <ChessTournamentForm /> 
       <HockeyTournamentForm />
       <TailoringInventory />
       <FootballPlayerManagement />
    </div>
  );
}

export default App;
