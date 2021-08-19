import './Navbar.scss';

export const Navbar = () => {
    return (
        <div className='navbar'>
            <div className='title'>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/activities">All activities</a></li>
                    <li><a href="/newactivity">Input manual activity</a></li>
                </ul>
            </div>
        </div>
    );
}