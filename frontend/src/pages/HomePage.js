import './HomePage.scss'
import { Navbar } from '../components/Navbar';

export const HomePage = () => {
    return (
        <div className='header'>
            <h1>ACTIVITY TRACKER</h1>
            <Navbar />
        </div>
    );
}