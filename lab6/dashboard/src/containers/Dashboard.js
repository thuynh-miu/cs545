import {useState} from 'react'
import Posts from '../components/Posts'
import PostModel from '../model/post'
import ChangeTitle from "../components/ChangeTitle";
import PostDetails from "../components/PostDetails";

const Dashboard = () => {
    const [selectedState, setSelectedState] = useState(0)
    const [postsState, setPostsState] = useState(PostModel.getAll())
    const [postState, setPostState] = useState({
        title: "",
        author: ""
    })

    const setSelected = (id) => {
        setSelectedState(id)
        const post = PostModel.getById(id)
        setPostState(post)
    }

    const onChangeTitle = (newTitle) => {
        PostModel.updateById(selectedState, newTitle)

        // Update postState with new title
        setPostState(prev => ({ ...prev, title: newTitle }));

        // Update postsState with the updated post
        setPostsState(prevPosts =>
            prevPosts.map(post =>
                post.id === selectedState ? { ...post, title: newTitle } : post
            )
        );
    };

    return (
        <div style={styles.dashboard}>
            <Posts posts={postsState}
                   setSelected={setSelected}/>

            <ChangeTitle title={postState.title}
                        onChangeTitle={onChangeTitle}/>

            <PostDetails post={postState}/>
        </div>
    )
}

const styles = {
    dashboard: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    }
}

export default Dashboard;