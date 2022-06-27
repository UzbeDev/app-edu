import Navigation from "./component/navigation";
import Region from "./page/country/region";
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import District from "./page/district/district";
import Default from "./page/default";
import Country from "./page/country/country";
import Todo from "./page/todo/Todo";
import InputAdd from "./page/todo/InputAdd";

function App() {
    return (
        <div>
            <BrowserRouter>
                <ToastContainer/>
                <Navigation/>
                <Routes>
                    <Route path='/' element={< Default/>}/>
                    <Route path='country' element={<Country/>}/>
                    <Route path='region' element={<Region/>}/>
                    <Route path='district' element={<District/>}/>
                    <Route path='todo' element={<Todo/>}/>
                    <Route path='inputAdd' element={<InputAdd/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
